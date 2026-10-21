package com.example.disco;

import android.app.Activity;
import android.media.MediaPlayer;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.microedition.midlet.MIDlet;

/**
 * Looping background music from assets/disco/*.mid. Safe to call from any thread.
 * Assets are copied to the cache dir first so playback works whether or not aapt compressed them.
 */
public final class MusicPlayer {
    private static MediaPlayer player;
    private static String current;
    private static boolean suspended;

    private MusicPlayer() {}

    /** Starts the track (looping). Does nothing if that track is already playing. */
    public static synchronized void play(String name) {
        if (name.equals(current) && player != null) {
            if (!suspended && !player.isPlaying()) {
                try { player.start(); } catch (Exception e) { Log.e("DiscoMusic", "restart failed", e); }
            }
            return;
        }
        release();
        Activity a = MIDlet.activity();
        if (a == null) return;
        try {
            File f = new File(a.getCacheDir(), name);
            if (!f.exists() || f.length() == 0) {
                InputStream in = a.getAssets().open("disco/" + name);
                try {
                    FileOutputStream out = new FileOutputStream(f);
                    try {
                        byte[] buf = new byte[4096];
                        int n;
                        while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
                    } finally {
                        out.close();
                    }
                } finally {
                    in.close();
                }
            }
            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(f.getAbsolutePath());
            mp.setLooping(true);
            mp.setVolume(0.8f, 0.8f);
            mp.prepare();
            player = mp;
            current = name;
            if (!suspended) mp.start();
        } catch (Exception e) {
            Log.e("DiscoMusic", "cannot play " + name, e);
            release();
        }
    }

    /** App went to the background. */
    public static synchronized void suspend() {
        suspended = true;
        try { if (player != null && player.isPlaying()) player.pause(); } catch (Exception e) { Log.e("DiscoMusic", "pause failed", e); }
    }

    /** App is back in the foreground. */
    public static synchronized void resume() {
        suspended = false;
        try { if (player != null && !player.isPlaying()) player.start(); } catch (Exception e) { Log.e("DiscoMusic", "resume failed", e); }
    }

    public static synchronized void release() {
        if (player != null) {
            try { player.release(); } catch (Exception ignored) {}
        }
        player = null;
        current = null;
    }
}
