package javax.microedition.midlet;

import android.app.Activity;

public abstract class MIDlet {
    private static volatile Activity activity;

    public static void attach(Activity a) { activity = a; }
    public static Activity activity() { return activity; }

    public abstract void startApp();
    public abstract void pauseApp();
    public abstract void destroyApp(boolean unconditional);

    public void notifyDestroyed() {
        final Activity a = activity;
        if (a != null) a.runOnUiThread(new Runnable() {
            @Override public void run() { a.finish(); }
        });
    }

    public void notifyPaused() {}
    public String getAppProperty(String key) { return null; }
}
