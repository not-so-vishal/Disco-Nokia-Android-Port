package javax.microedition.lcdui;

import android.graphics.Paint;
import android.graphics.Typeface;

public class Font {
    public static final int FACE_SYSTEM = 0, FACE_MONOSPACE = 32, FACE_PROPORTIONAL = 64;
    public static final int STYLE_PLAIN = 0, STYLE_BOLD = 1, STYLE_ITALIC = 2, STYLE_UNDERLINED = 4;
    public static final int SIZE_MEDIUM = 0, SIZE_SMALL = 8, SIZE_LARGE = 16;

    final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final int face, style, size;

    private Font(int face, int style, int size) {
        this.face = face; this.style = style; this.size = size;
        int s = (style & STYLE_BOLD) != 0 ? Typeface.BOLD : Typeface.NORMAL;
        if ((style & STYLE_ITALIC) != 0) s |= Typeface.ITALIC;
        Typeface base = face == FACE_MONOSPACE ? Typeface.MONOSPACE : Typeface.DEFAULT;
        paint.setTypeface(Typeface.create(base, s));
        paint.setTextSize(size == SIZE_SMALL ? 10 : size == SIZE_LARGE ? 16 : 12);
    }

    public static Font getFont(int face, int style, int size) { return new Font(face, style, size); }
    public static Font getDefaultFont() { return new Font(0, 0, 0); }
    public int getFace() { return face; }
    public int getStyle() { return style; }
    public int getSize() { return size; }
    public int getHeight() { Paint.FontMetrics m = paint.getFontMetrics(); return (int) Math.ceil(m.descent - m.ascent); }
    public int getBaselinePosition() { return (int) Math.ceil(-paint.getFontMetrics().ascent); }
    public int stringWidth(String s) { return (int) Math.ceil(paint.measureText(s == null ? "" : s)); }
    public int charWidth(char c) { return (int) Math.ceil(paint.measureText(String.valueOf(c))); }
}
