package com.example.disco;

// Android framework imports
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

public class DiscoGameView extends View {

    // Original game's logical screen size
    private static final int GAME_WIDTH = 176;
    private static final int GAME_HEIGHT = 208;

    // Background drawing area used by the original game
    private static final int BACKGROUND_SIZE = 128;
    private static final int BACKGROUND_X = 24;
    private static final int BACKGROUND_Y = 40;

    // Shared paint and clipping rectangle
    private final Paint paint = new Paint();
    private final Rect source = new Rect();
    private final Rect destination = new Rect();

    // Original game image resources
    private final Bitmap title;
    private final Bitmap stage;
    private final Bitmap floor;

    // Tap to switch between title and background preview
    private boolean showStage = false;

    public DiscoGameView(Context context) {
        super(context);

        // Preserve the sharp edges of the original pixel art
        paint.setFilterBitmap(false);
        paint.setAntiAlias(false);
        paint.setDither(false);

        // Load the image resources copied into app/src/main/assets/disco/
        title = loadBitmap("title.png");
        stage = loadBitmap("stage.png");
        floor = loadBitmap("floor.png");

        // Allow this view to receive keyboard focus
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    // Load an image from the assets/disco directory
    private Bitmap loadBitmap(String name) {
        try (InputStream input =
                     getContext().getAssets().open("disco/" + name)) {

            Bitmap bitmap = BitmapFactory.decodeStream(input);

            if (bitmap == null) {
                Log.e("Disco", "Invalid image: " + name);
            }

            return bitmap;

        } catch (IOException e) {
            Log.e("Disco", "Could not load: " + name, e);
            return null;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Clear the Android screen
        canvas.drawColor(Color.BLACK);

        // Fit the 176 x 208 game screen without stretching its proportions
        float scale = Math.min(
                getWidth() / (float) GAME_WIDTH,
                getHeight() / (float) GAME_HEIGHT
        );

        int width = Math.round(GAME_WIDTH * scale);
        int height = Math.round(GAME_HEIGHT * scale);

        // Center the logical game screen
        int left = (getWidth() - width) / 2;
        int top = (getHeight() - height) / 2;

        canvas.save();

        // Convert drawing coordinates to the game's logical coordinates
        canvas.translate(left, top);
        canvas.scale(scale, scale);

        if (showStage) {
            // Draw the tiled room background
            drawStagePreview(canvas);
        } else {
            // Draw the title image
            drawTitle(canvas);
        }

        canvas.restore();
    }

    // Draw the title screen
    private void drawTitle(Canvas canvas) {
        if (title != null) {
            source.set(
                    0,
                    0,
                    title.getWidth(),
                    title.getHeight()
            );

            // Keep the title centered in the logical screen
            destination.set(24, 40, 152, 168);

            canvas.drawBitmap(
                    title,
                    source,
                    destination,
                    paint
            );
        }

        // Temporary instruction for testing
        paint.setColor(Color.WHITE);
        paint.setTextSize(8);
        canvas.drawText("TAP TO PREVIEW", 48, 190, paint);
    }

    /**
     * Java ME Graphics anchor 20 is LEFT | TOP.
     *
     * x is the image's left edge.
     * y is the image's top edge.
     *
     * Important: do not subtract the image width or height here.
     */
    private void drawAnchoredImage(
            Canvas canvas,
            Bitmap bitmap,
            float x,
            float y,
            Paint imagePaint
    ) {
        canvas.drawBitmap(bitmap, x, y, imagePaint);
    }

    // Convert a packed RGB integer into an Android color
    private int rgbColor(int color) {
        return Color.rgb(
                (color >> 16) & 255,
                (color >> 8) & 255,
                color & 255
        );
    }

    // Draw one clipped piece of the stage texture
    private void drawStageTile(
            Canvas canvas,
            Bitmap bitmap,
            Paint imagePaint,
            int clipX,
            int clipY,
            int clipW,
            int clipH,
            float imageX,
            float imageY,
            boolean mirrored
    ) {
        canvas.save();

        // Restrict the image to the current wall tile
        canvas.clipRect(
                clipX,
                clipY,
                clipX + clipW,
                clipY + clipH
        );

        if (mirrored) {
            // Flip the image horizontally around its center
            canvas.save();

            float pivotX = imageX + bitmap.getWidth() / 2f;

            canvas.scale(-1f, 1f, pivotX, imageY);

            drawAnchoredImage(
                    canvas,
                    bitmap,
                    imageX,
                    imageY,
                    imagePaint
            );

            canvas.restore();

        } else {
            // Draw the normal, unflipped wall texture
            drawAnchoredImage(
                    canvas,
                    bitmap,
                    imageX,
                    imageY,
                    imagePaint
            );
        }

        canvas.restore();
    }

    // Draw the room using the original game's tile coordinates
    private void drawStagePreview(Canvas canvas) {

        // Stop if required background images failed to load
        if (stage == null || floor == null) {
            paint.setColor(Color.WHITE);
            paint.setTextSize(8);
            canvas.drawText("Missing stage.png or floor.png", 10, 195, paint);
            return;
        }

        Paint p = new Paint();
        p.setFilterBitmap(false);
        p.setAntiAlias(false);
        p.setDither(false);

        canvas.save();

        // The room occupies a 128 x 128 area at (24, 40)
        canvas.translate(BACKGROUND_X, BACKGROUND_Y);
        canvas.clipRect(
                0,
                0,
                BACKGROUND_SIZE,
                BACKGROUND_SIZE
        );

        // --------------------------------------------------
        // 1. LEFT WALL BACKGROUND COLOR
        // Taken from the original b.java room renderer.
        // --------------------------------------------------
        p.setColor(rgbColor(2239591));
        canvas.drawRect(0, 11, 51, 80, p);

        // --------------------------------------------------
        // 2. RIGHT WALL BACKGROUND COLOR
        // --------------------------------------------------
        p.setColor(rgbColor(725306));
        canvas.drawRect(51, 11, 128, 92, p);

        // --------------------------------------------------
        // 3. WALL TILE DETAIL
        // The original game reads this setting from its
        // configuration. Detail 4 is used for this preview.
        // --------------------------------------------------
        int detail = 4;

        int clipW = 2;
        int clipH = 2;
        int offset = 130;

        if (detail > 1 && detail <= 3) {
            clipW = 7;
            clipH = 9;
            offset = 123;
        } else if (detail > 3) {
            clipW = 14;
            clipH = 15;
            offset = 109;
        }

        // --------------------------------------------------
        // 4. LEFT WALL TILES
        // These coordinates follow the original b.java.
        // Anchor 20 means the image starts at (imageX, imageY).
        // --------------------------------------------------
        int x = 51 - clipW;
        int startY = 39 - clipH;

        while (x > -clipW) {
            int y = startY;

            while (y > -clipH) {
                float imageX = x - offset;

                drawStageTile(
                        canvas,
                        stage,
                        p,
                        x,
                        y,
                        clipW,
                        clipH,
                        imageX,
                        y,
                        false
                );

                // Move upward to the next tile
                y -= 15;
            }

            // Move to the next column
            x -= 14;

            // Follow the wall's diagonal perspective
            startY += 7;
        }

        // --------------------------------------------------
        // 5. RIGHT WALL TILES
        // Original b.java uses DirectGraphics transform 8192
        // to flip the stage image horizontally.
        // --------------------------------------------------
        x = 51;
        startY = 24;

        while (x < 128) {
            int y = startY;

            while (y > -15) {

                // Position taken from the original renderer
                float imageX =
                        x - (stage.getWidth() - offset - clipW);

                drawStageTile(
                        canvas,
                        stage,
                        p,
                        x,
                        y,
                        clipW,
                        clipH,
                        imageX,
                        y,
                        true
                );

                // Move upward to the next tile
                y -= 15;
            }

            // Move to the next column
            x += 14;

            // Follow the wall's diagonal perspective
            startY += 7;
        }

        // --------------------------------------------------
        // 6. FLOOR TILE SETTINGS
        // --------------------------------------------------
        int tileW = 22;
        int tileH = floor.getHeight();
        int stepX = 12;
        int halfH = tileH / 2;

        // Calculate the initial floor position
        int count = (128 - (51 - 11)) / tileW;
        int floorX = 51 - 11 - (count + 1) * tileW;
        int floorY = 39 + (count + 1) * tileH;

        int row = 0;

        // Preview phase; the original game calculates this
        // from its own animation/configuration state.
        int phase = 2;

        // --------------------------------------------------
        // 7. FLOOR TILES
        // Draw each tile inside its own clipping rectangle.
        // --------------------------------------------------
        while (floorX < 128) {
            int y = floorY;
            int shift = row + phase;

            while (y < 128 + tileH) {

                canvas.save();

                canvas.clipRect(
                        floorX,
                        y,
                        floorX + tileW,
                        y + tileH
                );

                // Java ME anchor 20 is LEFT | TOP
                drawAnchoredImage(
                        canvas,
                        floor,
                        floorX - shift * tileW,
                        y,
                        p
                );

                canvas.restore();

                // Move down to the next floor tile
                y += tileH;
            }

            // Keep the preview's original fixed phase
            if (phase != 2) {
                row = (row + 1) % 2;
            }

            // Move to the next floor column
            floorX += stepX;

            // Adjust the floor height to create perspective
            if (floorX <= 51 - 11) {
                floorY -= halfH;
            } else {
                floorY += halfH;
            }
        }

        // Restore the normal screen coordinates
        canvas.restore();

        // --------------------------------------------------
        // 8. TEMPORARY DEBUG LABEL
        // Remove this label when integrating gameplay.
        // --------------------------------------------------
        p.setColor(Color.WHITE);
        p.setTextSize(8);
        canvas.drawText("BACKGROUND TEST", 42, 195, p);
    }

    // Tap anywhere to switch between title and room preview
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            showStage = !showStage;
            invalidate();
            return true;
        }

        return true;
    }
}
