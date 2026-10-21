package com.example.disco;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.nokia.mid.appl.disco.GameMIDlet;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public class MainActivity extends Activity {
    private GameMIDlet game;
    private boolean paused;
    private Object backCallback; // android.window.OnBackInvokedCallback (API 33+)

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        if (Build.VERSION.SDK_INT >= 28) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }
        hideSystemUi();
        registerBackHandler();
        MIDlet.attach(this);
        game = new GameMIDlet();
        game.startApp();
    }

    @SuppressWarnings("deprecation")
    private void hideSystemUi() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /** Targeting API 36 the legacy Back key callbacks are no longer delivered, so register a real callback. */
    private void registerBackHandler() {
        if (Build.VERSION.SDK_INT >= 33) {
            android.window.OnBackInvokedCallback cb = new android.window.OnBackInvokedCallback() {
                @Override public void onBackInvoked() { handleBack(); }
            };
            backCallback = cb;
            getOnBackInvokedDispatcher().registerOnBackInvokedCallback(android.window.OnBackInvokedDispatcher.PRIORITY_DEFAULT, cb);
        }
    }

    private void handleBack() {
        Displayable d = Display.getDisplay(null).getCurrent();
        if (d != null) d.onBack();
    }

    @Override public boolean dispatchKeyEvent(KeyEvent ev) {
        if (ev.getKeyCode() == KeyEvent.KEYCODE_BACK && Build.VERSION.SDK_INT < 33) {
            if (ev.getAction() == KeyEvent.ACTION_DOWN && ev.getRepeatCount() == 0) handleBack();
            return true;
        }
        Displayable d = Display.getDisplay(null).getCurrent();
        if (d != null && d.onAndroidKey(ev)) return true;
        return super.dispatchKeyEvent(ev);
    }

    @Override public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) hideSystemUi();
    }

    @Override protected void onPause() {
        super.onPause();
        paused = true;
        MusicPlayer.suspend();
        if (game != null) game.pauseApp();
    }

    @Override protected void onResume() {
        super.onResume();
        MusicPlayer.resume();
        if (paused) {
            paused = false;
            // Resume through showNotify (not startApp, which would spawn a second game thread).
            Displayable d = Display.getDisplay(null).getCurrent();
            if (d instanceof Canvas) ((Canvas) d).showNotify();
        }
    }

    @Override protected void onDestroy() {
        if (Build.VERSION.SDK_INT >= 33 && backCallback != null) {
            getOnBackInvokedDispatcher().unregisterOnBackInvokedCallback((android.window.OnBackInvokedCallback) backCallback);
        }
        if (game != null) game.destroyApp(true);
        Display.reset();
        MusicPlayer.release();
        super.onDestroy();
    }
}
