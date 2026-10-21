package javax.microedition.lcdui;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.microedition.midlet.MIDlet;

public class Display {
    private static final Display INSTANCE = new Display();
    private final Handler ui = new Handler(Looper.getMainLooper());
    private volatile Displayable current;
    private volatile Runnable alertTask;
    private final AtomicBoolean refreshPosted = new AtomicBoolean();

    public static Display getDisplay(MIDlet m) { return INSTANCE; }

    /** Called when the Activity is destroyed so a relaunch starts clean. */
    public static void reset() {
        INSTANCE.cancelAlert();
        INSTANCE.current = null;
    }

    public boolean isColor() { return true; }
    public int numColors() { return 1 << 24; }
    public Displayable getCurrent() { return current; }

    public void setCurrent(Displayable d) {
        if (d == null) return;
        if (d instanceof Alert) {
            // MIDP: an Alert shown without a target returns to whatever was showing before.
            Displayable prev = current;
            if (prev instanceof Alert) prev = prev.getNextDisplayable();
            if (prev != d) d.setNextDisplayable(prev);
        }
        show(d);
    }

    public void setCurrent(Alert alert, Displayable next) {
        if (alert == null) { setCurrent(next); return; }
        alert.setNextDisplayable(next);
        show(alert);
    }

    public void callSerially(Runnable r) { ui.post(r); }

    private void show(final Displayable d) {
        final Displayable old = current;
        cancelAlert();
        current = d;
        if (old != d && old instanceof Canvas) ((Canvas) old).hideNotify();
        final Activity a = MIDlet.activity();
        if (a != null) {
            a.runOnUiThread(new Runnable() {
                @Override public void run() {
                    if (current != d) return; // superseded by a newer setCurrent
                    View v = d.makeView(a);
                    a.setContentView(v);
                    v.requestFocus();
                }
            });
        }
        if (old != d && d instanceof Canvas) ((Canvas) d).showNotify();
        if (d instanceof Alert) scheduleAlert((Alert) d);
    }

    /** Content of the visible screen changed (Form text, List items, commands). */
    void refresh(final Displayable d) {
        if (current != d || d instanceof Canvas) return;
        if (!d.rebuildOnChange()) {
            View v = d.view;
            if (v != null) v.postInvalidate();
            return;
        }
        final Activity a = MIDlet.activity();
        if (a == null || !refreshPosted.compareAndSet(false, true)) return;
        a.runOnUiThread(new Runnable() {
            @Override public void run() {
                refreshPosted.set(false);
                Displayable c = current;
                if (c == d) {
                    View v = d.makeView(a);
                    a.setContentView(v);
                    v.requestFocus();
                }
            }
        });
    }

    void dismissAlert(Alert a) {
        if (current != a) return;
        cancelAlert();
        Displayable n = a.getNextDisplayable();
        if (n != null && n != a) setCurrent(n);
    }

    private void scheduleAlert(final Alert alert) {
        int t = alert.getTimeout();
        if (t == Alert.FOREVER) return;
        Runnable r = new Runnable() {
            @Override public void run() { dismissAlert(alert); }
        };
        alertTask = r;
        ui.postDelayed(r, t);
    }

    private void cancelAlert() {
        Runnable r = alertTask;
        if (r != null) ui.removeCallbacks(r);
        alertTask = null;
    }
}
