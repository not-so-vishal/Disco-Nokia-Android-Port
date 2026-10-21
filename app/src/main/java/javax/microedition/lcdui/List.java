package javax.microedition.lcdui;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * MIDP List drawn full-screen in dp units: title, smooth scrolling rows (drag, fling, rubber-band),
 * and a button bar at the bottom that is clearly separated from the list.
 */
public class List extends Displayable {
    public static final int IMPLICIT = 3, EXCLUSIVE = 1, MULTIPLE = 2;
    public static final Command SELECT_COMMAND = new Command("Select", Command.OK, 0);

    private final CopyOnWriteArrayList<String> items = new CopyOnWriteArrayList<>();
    private volatile int selected = 0;
    private volatile boolean reveal = true; // scroll the selected row into view on the next draw
    private final int type;

    public List(String title, int type) { this.title = title == null ? "" : title; this.type = type; }

    public int append(String s, Image i) { items.add(s == null ? "" : s); changed(); return items.size() - 1; }
    public void insert(int at, String s, Image i) { items.add(Math.max(0, Math.min(at, items.size())), s == null ? "" : s); changed(); }
    public void set(int at, String s, Image i) { if (at >= 0 && at < items.size()) { items.set(at, s == null ? "" : s); changed(); } }

    public void delete(int i) {
        if (i < 0 || i >= items.size()) return;
        items.remove(i);
        if (selected >= items.size()) selected = Math.max(0, items.size() - 1);
        changed();
    }

    public void deleteAll() { items.clear(); selected = 0; reveal = true; changed(); }
    public int size() { return items.size(); }
    public int getSelectedIndex() { return items.isEmpty() ? -1 : selected; }
    public boolean isSelected(int i) { return i == selected; }
    public String getString(int i) { return items.get(i); }
    public int getType() { return type; }

    public void setSelectedIndex(int i, boolean on) {
        if (items.isEmpty()) return;
        selected = Math.max(0, Math.min(i, items.size() - 1));
        reveal = true;
        changed();
    }

    private void select() {
        if (items.isEmpty()) return;
        fire(SELECT_COMMAND);
    }

    @Override public boolean onAndroidKey(KeyEvent e) {
        int k = e.getKeyCode();
        boolean down = e.getAction() == KeyEvent.ACTION_DOWN;
        switch (k) {
            case KeyEvent.KEYCODE_DPAD_UP:
            case KeyEvent.KEYCODE_DPAD_DOWN:
                if (down && !items.isEmpty()) {
                    int n = items.size();
                    selected = k == KeyEvent.KEYCODE_DPAD_UP ? (selected + n - 1) % n : (selected + 1) % n;
                    reveal = true;
                    changed();
                }
                return true;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_BUTTON_A:
                if (down && e.getRepeatCount() == 0) select();
                return true;
            default:
                return false;
        }
    }

    @Override public View makeView(Context c) {
        View v = new ListView(c);
        view = v;
        return v;
    }

    private final class ListView extends View {
        private final float d;
        private final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final OverScroller scroller;
        private final int touchSlop, maxFling, minFling;
        private VelocityTracker velocity;

        private float scrollY, downX, downY, startScroll;
        private boolean dragging, firstLayout = true;
        private int pressedRow = -1, pressedButton = -1;
        private Command barLeft, barRight;

        // layout (pixels), recomputed every frame
        private float side, rowH, rowGap, topPad, listTop, listBottom, btnTop, btnBottom, btnH, gap, titleBase;

        ListView(Context c) {
            super(c);
            d = c.getResources().getDisplayMetrics().density;
            ViewConfiguration vc = ViewConfiguration.get(c);
            touchSlop = Math.max(1, vc.getScaledTouchSlop() / 2);   // react early so dragging feels direct
            maxFling = vc.getScaledMaximumFlingVelocity();
            minFling = vc.getScaledMinimumFlingVelocity();
            scroller = new OverScroller(c);
            setFocusable(true);
            setFocusableInTouchMode(true);
        }

        private void layout() {
            side = 12 * d;
            rowH = 56 * d;
            rowGap = 8 * d;
            topPad = 4 * d;
            btnH = 52 * d;
            gap = 28 * d;                                  // clear space between the list and the buttons
            float bottomMargin = 28 * d;
            titleBase = 28 * d + 28 * d;
            listTop = 28 * d + 48 * d;
            btnBottom = getHeight() - bottomMargin;
            btnTop = btnBottom - btnH;
            listBottom = btnTop - gap;
            ArrayList<Command> c = sortedCommands();
            barLeft = barRight = null;
            if (c.size() == 1) {
                barRight = c.get(0);                       // a lone Back/Exit sits on the right
            } else if (c.size() >= 2) {
                barLeft = c.get(0);
                barRight = c.get(c.size() - 1);            // Back/Cancel is always right-most
            }
        }

        private float contentHeight() { return items.size() * (rowH + rowGap) + topPad; }
        private float maxScroll() { return Math.max(0f, contentHeight() - (listBottom - listTop)); }

        private void revealSelected() {
            float viewH = listBottom - listTop;
            float top = topPad + selected * (rowH + rowGap);
            float bottom = top + rowH;
            float target = scrollY;
            if (top < scrollY) target = top - topPad;
            else if (bottom > scrollY + viewH) target = bottom - viewH + topPad;
            target = Math.max(0f, Math.min(target, maxScroll()));
            if (firstLayout) {
                scrollY = target;
            } else if (target != scrollY) {
                scroller.forceFinished(true);
                scroller.startScroll(0, (int) scrollY, 0, (int) (target - scrollY), 220);
            }
        }

        private void fitText(android.graphics.Canvas cv, String s, float cx, float baseline, float size, int color, boolean bold, float maxW) {
            p.setTypeface(Typeface.create("sans-serif", bold ? Typeface.BOLD : Typeface.NORMAL));
            p.setColor(color);
            p.setTextAlign(Paint.Align.CENTER);
            float sz = size;
            p.setTextSize(sz);
            while (p.measureText(s) > maxW && sz > 9 * d) { sz -= 0.5f * d; p.setTextSize(sz); }
            while (p.measureText(s) > maxW && s.length() > 4) { s = s.substring(0, s.length() - 2); }
            cv.drawText(s, cx, baseline, p);
        }

        private void drawButton(android.graphics.Canvas cv, Command c, float x1, float x2, boolean pressed) {
            if (c == null) return;
            p.setStyle(Paint.Style.FILL);
            p.setColor(Theme.HILITE);
            cv.drawRoundRect(new RectF(x1, btnTop, x2, btnBottom), 14 * d, 14 * d, p);
            p.setColor(pressed ? 0xff7a2a82 : 0xff4a1a50);
            cv.drawRoundRect(new RectF(x1 + 2 * d, btnTop + 2 * d, x2 - 2 * d, btnBottom - 2 * d), 12 * d, 12 * d, p);
            fitText(cv, c.getLabel(), (x1 + x2) / 2f, btnTop + btnH / 2f + 6 * d, 17 * d, Theme.FG, true, x2 - x1 - 24 * d);
        }

        private float leftX1() { return side; }
        private float leftX2() { return getWidth() / 2f - side / 2f; }
        private float rightX1() { return getWidth() / 2f + side / 2f; }
        private float rightX2() { return getWidth() - side; }

        @Override protected void onDraw(android.graphics.Canvas cv) {
            layout();
            if (reveal) { reveal = false; revealSelected(); }
            firstLayout = false;
            if (scroller.computeScrollOffset()) {
                scrollY = scroller.getCurrY();
                postInvalidateOnAnimation();
            }
            cv.drawColor(Theme.BG);
            float w = getWidth();

            fitText(cv, title, w / 2f, titleBase, 22 * d, Theme.FG, true, w - 2 * side);

            int rows = items.size();
            int save = cv.save();
            cv.clipRect(0f, listTop, w, listBottom);
            for (int i = 0; i < rows; i++) {
                float y = listTop + topPad + i * (rowH + rowGap) - scrollY;
                if (y + rowH < listTop || y > listBottom) continue;
                p.setStyle(Paint.Style.FILL);
                p.setColor(i == selected ? Theme.HILITE : (i == pressedRow ? 0xff4d4d57 : Theme.PANEL));
                cv.drawRoundRect(new RectF(side, y, w - side, y + rowH), 14 * d, 14 * d, p);
                fitText(cv, items.get(i), w / 2f, y + rowH / 2f + 6 * d, 18 * d, Theme.FG, i == selected, w - 2 * side - 28 * d);
            }
            float total = contentHeight(), viewH = listBottom - listTop;
            if (total > viewH) {
                float barH = Math.max(24 * d, viewH * viewH / total);
                float frac = maxScroll() > 0 ? Math.max(0f, Math.min(1f, scrollY / maxScroll())) : 0f;
                float barY = listTop + (viewH - barH) * frac;
                p.setColor(Theme.DIM);
                cv.drawRoundRect(new RectF(w - 5 * d, barY, w - 2 * d, barY + barH), 2 * d, 2 * d, p);
            }
            cv.restoreToCount(save);

            // divider in the middle of the gap, then the buttons
            p.setStyle(Paint.Style.FILL);
            p.setColor(Theme.PANEL);
            float divY = listBottom + gap / 2f;
            cv.drawRect(side * 2, divY, w - side * 2, divY + Math.max(1f, d), p);
            drawButton(cv, barLeft, leftX1(), leftX2(), pressedButton == 0);
            drawButton(cv, barRight, rightX1(), rightX2(), pressedButton == 1);
        }

        private int rowAt(float y) {
            if (y < listTop || y > listBottom) return -1;
            int i = (int) ((y - listTop - topPad + scrollY) / (rowH + rowGap));
            float within = (y - listTop - topPad + scrollY) - i * (rowH + rowGap);
            return (i >= 0 && i < items.size() && within <= rowH) ? i : -1;
        }

        private int buttonAt(float x, float y) {
            if (y < btnTop - 6 * d) return -1;
            if (x < getWidth() / 2f) return barLeft != null ? 0 : -1;
            return barRight != null ? 1 : -1;
        }

        /** Pulling past the ends gives resistance (rubber band). */
        private float soft(float s) {
            float max = maxScroll();
            if (s < 0) return s * 0.35f;
            if (s > max) return max + (s - max) * 0.35f;
            return s;
        }

        @Override public boolean onTouchEvent(MotionEvent e) {
            if (velocity == null) velocity = VelocityTracker.obtain();
            velocity.addMovement(e);
            float x = e.getX(), y = e.getY();
            switch (e.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    scroller.forceFinished(true);
                    downX = x; downY = y; startScroll = scrollY; dragging = false;
                    pressedButton = buttonAt(x, y);
                    pressedRow = pressedButton >= 0 ? -1 : rowAt(y);
                    invalidate();
                    return true;
                case MotionEvent.ACTION_MOVE: {
                    float dy = y - downY;
                    if (!dragging && pressedButton < 0 && Math.abs(dy) > touchSlop) {
                        dragging = true;
                        pressedRow = -1;
                        startScroll = scrollY;
                        downY = y;            // start the drag from here: no initial jump
                        dy = 0;
                    }
                    if (dragging) {
                        scrollY = soft(startScroll - dy);
                        invalidate();
                    } else if (pressedButton >= 0 && buttonAt(x, y) != pressedButton) {
                        pressedButton = -1;
                        invalidate();
                    }
                    return true;
                }
                case MotionEvent.ACTION_UP: {
                    int btn = pressedButton, row = pressedRow;
                    boolean wasDragging = dragging;
                    pressedButton = -1; pressedRow = -1; dragging = false;
                    if (wasDragging) {
                        velocity.computeCurrentVelocity(1000, maxFling);
                        float vy = -velocity.getYVelocity();
                        float max = maxScroll();
                        if (scrollY < 0 || scrollY > max) {
                            scroller.springBack(0, (int) scrollY, 0, 0, 0, (int) max);
                        } else if (Math.abs(vy) > minFling) {
                            scroller.fling(0, (int) scrollY, 0, (int) vy, 0, 0, 0, (int) max, 0, (int) (28 * d));
                        }
                    } else if (btn >= 0 && buttonAt(x, y) == btn) {
                        fire(btn == 0 ? barLeft : barRight);
                    } else if (row >= 0 && rowAt(y) == row) {
                        selected = row;
                        invalidate();
                        select();
                    }
                    recycleVelocity();
                    invalidate();
                    return true;
                }
                case MotionEvent.ACTION_CANCEL:
                    pressedButton = -1; pressedRow = -1; dragging = false;
                    if (scrollY < 0 || scrollY > maxScroll()) scroller.springBack(0, (int) scrollY, 0, 0, 0, (int) maxScroll());
                    recycleVelocity();
                    invalidate();
                    return true;
                default:
                    return true;
            }
        }

        private void recycleVelocity() {
            if (velocity != null) { velocity.recycle(); velocity = null; }
        }
    }
}
