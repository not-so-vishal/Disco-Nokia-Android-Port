package javax.microedition.lcdui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Graphics {
    public static final int HCENTER = 1, VCENTER = 2, LEFT = 4, RIGHT = 8, TOP = 16, BOTTOM = 32, BASELINE = 64;
    // Nokia DirectGraphics manipulation flags
    public static final int FLIP_HORIZONTAL = 0x2000, FLIP_VERTICAL = 0x4000;

    final Canvas canvas;
    // No anti-alias / bitmap filtering: keeps pixel art and rect edges crisp when scaled (text re-enables AA)
    final Paint paint = new Paint();
    private final Paint imagePaint = new Paint(); // MIDP drawImage ignores the current colour/alpha
    private Font font = Font.getFont(0, 0, 0);
    private int base;      // canvas save level that represents "no clip, current translate not applied"
    private int tx, ty;    // accumulated MIDP translate

    public Graphics(Canvas c) {
        canvas = c;
        base = c.save();
        setFont(font);
    }

    public void setColor(int rgb) { paint.setColor(0xff000000 | (rgb & 0xffffff)); }
    public void setColor(int r, int g, int b) { paint.setARGB(255, r & 255, g & 255, b & 255); }
    public void setARGBColor(int a, int r, int g, int b) { paint.setARGB(a & 255, r & 255, g & 255, b & 255); }

    public void fillRect(int x, int y, int w, int h) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + w, y + h, paint);
    }

    public void drawRect(int x, int y, int w, int h) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x + w, y + h, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    public void fillRoundRect(int x, int y, int w, int h, int aw, int ah) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new android.graphics.RectF(x, y, x + w, y + h), aw / 2f, ah / 2f, paint);
    }

    public void drawRoundRect(int x, int y, int w, int h, int aw, int ah) {
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(new android.graphics.RectF(x, y, x + w, y + h), aw / 2f, ah / 2f, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    /** MIDP setClip REPLACES the clip. Android's clipRect only intersects, so reset first. */
    public void setClip(int x, int y, int w, int h) {
        canvas.restoreToCount(base);
        base = canvas.save();
        canvas.translate(tx, ty);
        canvas.clipRect(x, y, x + w, y + h);
    }

    /** MIDP clipRect intersects with the current clip. */
    public void clipRect(int x, int y, int w, int h) { canvas.clipRect(x, y, x + w, y + h); }

    public void translate(int x, int y) {
        canvas.translate(x, y);
        tx += x;
        ty += y;
    }

    public void setFont(Font f) {
        if (f != null) {
            font = f;
            paint.setTypeface(f.paint.getTypeface());
            paint.setTextSize(f.paint.getTextSize());
        }
    }

    public void drawString(String s, int x, int y, int anchor) {
        if (s == null) return;
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        Paint.FontMetrics fm = paint.getFontMetrics();
        float xx = x, yy = y;
        if ((anchor & HCENTER) != 0) xx -= paint.measureText(s) / 2f;
        else if ((anchor & RIGHT) != 0) xx -= paint.measureText(s);
        if ((anchor & TOP) != 0) yy -= fm.ascent;        // ascent is negative
        else if ((anchor & BOTTOM) != 0) yy -= fm.descent;
        else if ((anchor & VCENTER) != 0) yy += font.getHeight() / 2f;
        canvas.drawText(s, xx, yy, paint);
        paint.setAntiAlias(false);
    }

    public void drawImage(Image i, int x, int y, int anchor) { drawImage(i, x, y, anchor, 0); }

    /** Draws an image with optional Nokia flip/rotate flags (used by DirectGraphics). */
    public void drawImage(Image i, int x, int y, int anchor, int manipulation) {
        if (i == null) return;
        int w = i.getWidth(), h = i.getHeight();
        float xx = x, yy = y;
        if ((anchor & HCENTER) != 0) xx -= w / 2f;
        else if ((anchor & RIGHT) != 0) xx -= w;
        if ((anchor & VCENTER) != 0) yy -= h / 2f;
        else if ((anchor & BOTTOM) != 0) yy -= h;
        if (manipulation == 0) {
            canvas.drawBitmap(i.bitmap, xx, yy, imagePaint);
            return;
        }
        canvas.save();
        canvas.translate(xx + w / 2f, yy + h / 2f);
        int rot = manipulation & 0x1ff;               // 0, 90, 180, 270
        if (rot != 0) canvas.rotate(-rot);            // Nokia rotates counter-clockwise
        canvas.scale((manipulation & FLIP_HORIZONTAL) != 0 ? -1 : 1, (manipulation & FLIP_VERTICAL) != 0 ? -1 : 1);
        canvas.drawBitmap(i.bitmap, -w / 2f, -h / 2f, imagePaint);
        canvas.restore();
    }

    public void drawRegion(Image i, int sx, int sy, int w, int h, int transform, int x, int y, int anchor) {
        Image sub = Image.createImage(i, sx, sy, w, h, transform);
        drawImage(sub, x, y, anchor);
    }

    public void drawRGB(int[] rgb, int offset, int scan, int x, int y, int w, int h, boolean alpha) {
        Bitmap b = Bitmap.createBitmap(rgb, offset, scan, w, h, Bitmap.Config.ARGB_8888);
        canvas.drawBitmap(b, x, y, paint);
        b.recycle();
    }

    public void drawLine(int x1, int y1, int x2, int y2) { canvas.drawLine(x1, y1, x2, y2, paint); }
    public Canvas getCanvas() { return canvas; }
}
