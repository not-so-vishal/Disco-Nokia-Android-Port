package javax.microedition.lcdui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * MIDP Canvas on a 176x208 virtual screen, scaled (letterboxed) onto the Android view.
 * Touch zones: bottom strip = soft keys, edges = d-pad, centre = FIRE.
 */
public class Canvas extends Displayable {
    public static final int UP = 1, LEFT = 2, RIGHT = 5, DOWN = 6, FIRE = 8, GAME_A = 9, GAME_B = 10, GAME_C = 11, GAME_D = 12;
    public static final int KEY_NUM0 = 48, KEY_NUM1 = 49, KEY_NUM2 = 50, KEY_NUM3 = 51, KEY_NUM4 = 52,
            KEY_NUM5 = 53, KEY_NUM6 = 54, KEY_NUM7 = 55, KEY_NUM8 = 56, KEY_NUM9 = 57, KEY_STAR = 42, KEY_POUND = 35;
    static final int W = 176, H = 208;

    private final Object paintLock = new Object();
    private boolean painted;

    protected void paint(Graphics g) {}

    /**
     * Labels of native touch buttons drawn below the game (max 3), or null for none.
     * Button i sends key BUTTON_KEYS[i]. When there are no buttons, tapping anywhere acts as FIRE.
     */
    protected String[] touchButtons() { return null; }

    /** Whether button i is currently "on" (drawn highlighted). */
    protected boolean touchButtonActive(int index) { return false; }

    private static final int[] BUTTON_KEYS = { -7, -5, -6 };
    protected void keyPressed(int key) {}
    protected void keyReleased(int key) {}
    protected void keyRepeated(int key) {}
    protected void pointerPressed(int x, int y) {}
    protected void pointerReleased(int x, int y) {}
    public void setFullScreenMode(boolean b) {}
    public boolean hasPointerEvents() { return true; }

    /** Nokia key codes (-1..-5 d-pad/centre, 2/4/5/6/8 numpad) to MIDP game actions. */
    public int getGameAction(int k) {
        switch (k) {
            case -1: case KEY_NUM2: return UP;
            case -2: case KEY_NUM8: return DOWN;
            case -3: case KEY_NUM4: return LEFT;
            case -4: case KEY_NUM6: return RIGHT;
            case -5: case KEY_NUM5: return FIRE;
            case KEY_NUM1: return GAME_A;
            case KEY_NUM3: return GAME_B;
            case KEY_NUM7: return GAME_C;
            case KEY_NUM9: return GAME_D;
            default: return 0;
        }
    }

    public int getKeyCode(int action) {
        switch (action) {
            case UP: return -1;
            case DOWN: return -2;
            case LEFT: return -3;
            case RIGHT: return -4;
            case FIRE: return -5;
            default: return 0;
        }
    }

    @Override public int getWidth() { return W; }
    @Override public int getHeight() { return H; }
    public void hideNotify() {}
    public void showNotify() {}

    public void repaint() {
        View v = view;
        if (v != null) v.postInvalidate();
    }

    public void repaint(int x, int y, int w, int h) { repaint(); }

    /**
     * Blocks the game thread until the frame has actually been drawn (or 40 ms pass).
     * This paces the game loop to the display and avoids calling invalidate() off the UI thread.
     */
    public void serviceRepaints() {
        View v = view;
        if (v == null) return;
        if (Looper.myLooper() == Looper.getMainLooper()) { v.invalidate(); return; }
        synchronized (paintLock) {
            painted = false;
            v.postInvalidate();
            long end = System.currentTimeMillis() + 40;
            while (!painted) {
                long left = end - System.currentTimeMillis();
                if (left <= 0) break;
                try { paintLock.wait(left); } catch (InterruptedException e) { Thread.currentThread().interrupt(); break; }
            }
        }
    }

    @Override public View makeView(Context c) {
        CanvasView v = new CanvasView(c);
        view = v;
        return v;
    }

    static int mapKey(int k) {
        switch (k) {
            case KeyEvent.KEYCODE_DPAD_UP: case KeyEvent.KEYCODE_W: return -1;
            case KeyEvent.KEYCODE_DPAD_DOWN: case KeyEvent.KEYCODE_S: return -2;
            case KeyEvent.KEYCODE_DPAD_LEFT: case KeyEvent.KEYCODE_A: return -3;
            case KeyEvent.KEYCODE_DPAD_RIGHT: case KeyEvent.KEYCODE_D: return -4;
            case KeyEvent.KEYCODE_DPAD_CENTER: case KeyEvent.KEYCODE_ENTER: case KeyEvent.KEYCODE_SPACE:
            case KeyEvent.KEYCODE_BUTTON_A: return -5;
            case KeyEvent.KEYCODE_MENU: return -7;
            case KeyEvent.KEYCODE_STAR: return KEY_STAR;
            case KeyEvent.KEYCODE_POUND: return KEY_POUND;
            default:
                if (k >= KeyEvent.KEYCODE_0 && k <= KeyEvent.KEYCODE_9) return KEY_NUM0 + (k - KeyEvent.KEYCODE_0);
                return 0;
        }
    }

    @Override public boolean onAndroidKey(KeyEvent e) {
        int code = mapKey(e.getKeyCode());
        if (code == 0) return false;
        try {
            if (e.getAction() == KeyEvent.ACTION_DOWN) {
                if (e.getRepeatCount() == 0) keyPressed(code); else keyRepeated(code);
            } else if (e.getAction() == KeyEvent.ACTION_UP) {
                keyReleased(code);
            }
        } catch (Throwable t) {
            Log.e("DiscoCanvas", "key handler failed", t);
        }
        return true;
    }

    /** Android Back opens the game menu, like the right soft key. */
    @Override public void onBack() {
        try { keyPressed(-7); } catch (Throwable t) { Log.e("DiscoCanvas", "back handler failed", t); }
    }

    private final class CanvasView extends View {
        private final float density;
        private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float scale = 1f, ox, oy;
        private final float[] bl = new float[3], bt = new float[3], br = new float[3], bb = new float[3];
        private int buttonCount;
        private int pressed = -1;
        private int held;

        CanvasView(Context c) {
            super(c);
            density = c.getResources().getDisplayMetrics().density;
            setFocusable(true);
            setFocusableInTouchMode(true);
        }

        /** Game area on top, a generous gap, then a row of large buttons. */
        private void layout(String[] labels) {
            buttonCount = labels == null ? 0 : Math.min(labels.length, 3);
            float d = density, vw = getWidth(), vh = getHeight();
            if (buttonCount == 0) {
                scale = Math.min(vw / W, vh / H);
                ox = (vw - W * scale) / 2f;
                oy = (vh - H * scale) / 2f;
                return;
            }
            float gap = 28 * d, btnH = 52 * d, bottom = 28 * d, side = 12 * d, space = 8 * d;
            scale = Math.min(vw / W, (vh - gap - btnH - bottom) / H);
            float block = H * scale + gap + btnH;
            oy = Math.max(0f, (vh - bottom - block) / 2f);
            ox = (vw - W * scale) / 2f;
            float top = oy + H * scale + gap;
            float bw = (vw - 2 * side - (buttonCount - 1) * space) / buttonCount;
            for (int i = 0; i < buttonCount; i++) {
                bl[i] = side + i * (bw + space);
                br[i] = bl[i] + bw;
                bt[i] = top;
                bb[i] = top + btnH;
            }
        }

        private int hit(float x, float y) {
            for (int i = 0; i < buttonCount; i++) {
                if (x >= bl[i] && x <= br[i] && y >= bt[i] && y <= bb[i]) return i;
            }
            return -1;
        }

        private void drawButtons(android.graphics.Canvas c, String[] labels) {
            float d = density;
            for (int i = 0; i < buttonCount; i++) {
                boolean on = touchButtonActive(i);
                boolean down = pressed == i;
                p.setStyle(Paint.Style.FILL);
                p.setColor(0xffd44ac8);
                c.drawRoundRect(new RectF(bl[i], bt[i], br[i], bb[i]), 12 * d, 12 * d, p);
                p.setColor(on || down ? 0xff9d238e : 0xff4a1a50);
                c.drawRoundRect(new RectF(bl[i] + 2 * d, bt[i] + 2 * d, br[i] - 2 * d, bb[i] - 2 * d), 10 * d, 10 * d, p);
                p.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
                p.setTextAlign(Paint.Align.CENTER);
                p.setColor(0xfff6edf7);
                float size = 16 * d, maxW = br[i] - bl[i] - 12 * d;
                p.setTextSize(size);
                while (p.measureText(labels[i]) > maxW && size > 9 * d) { size -= 0.5f * d; p.setTextSize(size); }
                c.drawText(labels[i], (bl[i] + br[i]) / 2f, (bt[i] + bb[i]) / 2f + size * 0.35f, p);
            }
        }

        @Override protected void onDraw(android.graphics.Canvas c) {
            String[] labels = touchButtons();
            layout(labels);
            c.drawColor(Color.BLACK);
            int sc = c.save();
            c.translate(ox, oy);
            c.scale(scale, scale);
            c.clipRect(0, 0, W, H);
            try {
                paint(new Graphics(c));
            } catch (Throwable t) {
                Log.e("DiscoCanvas", "paint failed", t);
            }
            c.restoreToCount(sc);
            if (buttonCount > 0) drawButtons(c, labels);
            synchronized (paintLock) {
                painted = true;
                paintLock.notifyAll();
            }
        }

        @Override public boolean onTouchEvent(MotionEvent e) {
            try {
                int action = e.getActionMasked();
                if (buttonCount > 0) {
                    switch (action) {
                        case MotionEvent.ACTION_DOWN:
                            pressed = hit(e.getX(), e.getY());
                            invalidate();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (pressed >= 0 && hit(e.getX(), e.getY()) != pressed) { pressed = -1; invalidate(); }
                            break;
                        case MotionEvent.ACTION_UP: {
                            int hitNow = hit(e.getX(), e.getY());
                            int was = pressed;
                            pressed = -1;
                            invalidate();
                            if (was >= 0 && was == hitNow) {
                                int key = BUTTON_KEYS[was];
                                keyPressed(key);
                                keyReleased(key);
                            }
                            break;
                        }
                        case MotionEvent.ACTION_CANCEL:
                            pressed = -1;
                            invalidate();
                            break;
                        default:
                            break;
                    }
                } else if (action == MotionEvent.ACTION_DOWN) {
                    held = -5;           // title screen etc.: tap anywhere = FIRE
                    keyPressed(held);
                } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                    if (held != 0) { int k = held; held = 0; keyReleased(k); }
                }
            } catch (Throwable t) {
                Log.e("DiscoCanvas", "touch handler failed", t);
            }
            return true;
        }
    }
}
