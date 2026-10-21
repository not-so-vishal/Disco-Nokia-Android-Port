package javax.microedition.lcdui;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

public class Displayable {
    // Copy-on-write: the game thread edits commands while the UI thread draws them.
    protected final CopyOnWriteArrayList<Command> commands = new CopyOnWriteArrayList<>();
    protected volatile CommandListener listener;
    protected volatile String title = "";
    protected volatile View view;
    protected volatile Displayable nextDisplayable;

    public void setNextDisplayable(Displayable d) { nextDisplayable = d; }
    public Displayable getNextDisplayable() { return nextDisplayable; }

    public void addCommand(Command c) {
        if (c != null && commands.addIfAbsent(c)) changed();
    }

    public void removeCommand(Command c) {
        if (commands.remove(c)) changed();
    }

    public void setCommandListener(CommandListener l) { listener = l; }
    public void setTitle(String t) { title = t == null ? "" : t; changed(); }
    public String getTitle() { return title; }
    public int getWidth() { return 176; }
    public int getHeight() { return 208; }
    public boolean isShown() { return Display.getDisplay(null).getCurrent() == this; }

    /** Builds the Android view for this screen. Always called on the UI thread. */
    public View makeView(Context c) {
        android.widget.TextView t = new android.widget.TextView(c);
        t.setText(title);
        view = t;
        return t;
    }

    /** Whether the Android view must be rebuilt when content changes (Forms), vs. just redrawn. */
    protected boolean rebuildOnChange() { return false; }

    protected void changed() { Display.getDisplay(null).refresh(this); }

    public void fire(Command c) {
        CommandListener l = listener;
        if (l != null && c != null) l.commandAction(c, this);
    }

    /** Hardware keyboard / d-pad events. Return true if consumed. */
    public boolean onAndroidKey(KeyEvent e) { return false; }

    /** Android Back button / gesture. */
    public void onBack() {
        Command b = backCommand();
        if (b != null) fire(b);
    }

    /** Back/Cancel first, then Exit. */
    protected Command backCommand() {
        for (Command c : commands) {
            int t = c.getCommandType();
            if (t == Command.BACK || t == Command.CANCEL) return c;
        }
        for (Command c : commands) if (c.getCommandType() == Command.EXIT) return c;
        return null;
    }

    /** Commands in on-screen order: exit/others on the left, Back/Cancel on the right. */
    protected ArrayList<Command> sortedCommands() {
        ArrayList<Command> l = new ArrayList<>(commands);
        Collections.sort(l, new Comparator<Command>() {
            @Override public int compare(Command a, Command b) {
                int wa = weight(a), wb = weight(b);
                return wa != wb ? wa - wb : a.getPriority() - b.getPriority();
            }
        });
        return l;
    }

    private static int weight(Command c) {
        int t = c.getCommandType();
        if (t == Command.BACK || t == Command.CANCEL) return 2; // Back always on the right
        if (t == Command.EXIT) return 0;
        return 1;
    }
}
