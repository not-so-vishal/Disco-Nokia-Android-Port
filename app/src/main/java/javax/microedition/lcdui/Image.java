package javax.microedition.lcdui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.midlet.MIDlet;

public class Image {
    final Bitmap bitmap;

    private Image(Bitmap b) { bitmap = b; }

    /** Loads "/name.png" from assets/disco/. */
    public static Image createImage(String path) throws IOException {
        String p = path.startsWith("/") ? path.substring(1) : path;
        InputStream in = MIDlet.activity().getAssets().open("disco/" + p);
        try {
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inScaled = false;
            Bitmap b = BitmapFactory.decodeStream(in, null, o);
            if (b == null) throw new IOException("Cannot decode " + path);
            return new Image(b);
        } finally {
            in.close();
        }
    }

    public static Image createImage(byte[] data, int off, int len) {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        Bitmap b = BitmapFactory.decodeByteArray(data, off, len, o);
        if (b == null) throw new IllegalArgumentException("Cannot decode image data");
        return new Image(b);
    }

    /** Mutable images start white, as in MIDP. */
    public static Image createImage(int w, int h) {
        Bitmap b = Bitmap.createBitmap(Math.max(1, w), Math.max(1, h), Bitmap.Config.ARGB_8888);
        b.eraseColor(0xffffffff);
        return new Image(b);
    }

    public static Image createImage(Image src) {
        return new Image(src.bitmap.copy(Bitmap.Config.ARGB_8888, false));
    }

    public static Image createImage(Image src, int x, int y, int w, int h, int transform) {
        Matrix m = null;
        switch (transform) {            // javax.microedition.lcdui.game.Sprite TRANS_* values
            case 1: m = new Matrix(); m.setScale(1, -1); break;                                   // mirror + rot180
            case 2: m = new Matrix(); m.setScale(-1, 1); break;                                   // mirror
            case 3: m = new Matrix(); m.setRotate(180); break;
            case 4: m = new Matrix(); m.setRotate(270); m.postScale(-1, 1); break;                // mirror + rot270
            case 5: m = new Matrix(); m.setRotate(90); break;
            case 6: m = new Matrix(); m.setRotate(270); break;
            case 7: m = new Matrix(); m.setRotate(90); m.postScale(-1, 1); break;                 // mirror + rot90
            default: break;
        }
        return new Image(Bitmap.createBitmap(src.bitmap, x, y, w, h, m, false));
    }

    public int getWidth() { return bitmap.getWidth(); }
    public int getHeight() { return bitmap.getHeight(); }
    public boolean isMutable() { return bitmap.isMutable(); }
    public Graphics getGraphics() { return new Graphics(new Canvas(bitmap)); }
}
