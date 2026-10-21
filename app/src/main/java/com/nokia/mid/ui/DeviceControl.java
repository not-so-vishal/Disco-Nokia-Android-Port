package com.nokia.mid.ui;

import android.app.Activity;
import android.view.WindowManager;
import javax.microedition.midlet.MIDlet;

/** Backlight control: any non-zero level keeps the screen awake. */
public final class DeviceControl {
    private DeviceControl() {}

    public static void setLights(int target, int level) {
        final Activity a = MIDlet.activity();
        if (a == null) return;
        final boolean on = level > 0;
        a.runOnUiThread(new Runnable() {
            @Override public void run() {
                if (on) a.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                else a.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });
    }

    public static void setLights(int target, int level, int duration) { setLights(target, level); }
    public static void flashLights(long duration) {}
    public static void startVibra(int freq, long duration) {}
    public static void stopVibra() {}
}
