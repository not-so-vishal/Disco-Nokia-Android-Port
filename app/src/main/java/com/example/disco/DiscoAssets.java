
package com.example.disco;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public final class DiscoAssets {
    public final Bitmap title;
    public final Bitmap stage;
    public final Bitmap floor;
    public final Bitmap light;
    public final Bitmap player;
    public final Bitmap body;
    public final Bitmap legs;
    public final Bitmap table;
    public final Bitmap symbols;
    public final Bitmap numbers;
    public final Bitmap softkey;

    public DiscoAssets(Context context) {
        title   = load(context, "title.png");
        stage   = load(context, "stage.png");
        floor   = load(context, "floor.png");
        light   = load(context, "light.png");
        player  = load(context, "player.png");
        body    = load(context, "body.png");
        legs    = load(context, "legs.png");
        table   = load(context, "table.png");
        symbols = load(context, "symbols.png");
        numbers = load(context, "numbers.png");
        softkey = load(context, "softkey.png");
    }

    private Bitmap load(Context context, String filename) {
        try (InputStream input = context.getAssets()
                .open("disco/" + filename)) {
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            Log.e("DiscoAssets", "Failed to load " + filename, e);
            return null;
        }
    }
}
