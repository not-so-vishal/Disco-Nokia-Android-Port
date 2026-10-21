package com.example.disco;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;
import javax.microedition.midlet.MIDlet;

/** Native "type a number" dialog used by the cheat menu. */
public final class NumberPrompt {
    public interface Callback {
        void onValue(long value);
    }

    private NumberPrompt() {}

    public static void show(final String title, final long current, final Callback callback) {
        final Activity a = MIDlet.activity();
        if (a == null) return;
        a.runOnUiThread(new Runnable() {
            @Override public void run() {
                final EditText input = new EditText(a);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setText(String.valueOf(current));
                input.setSelectAllOnFocus(true);
                AlertDialog.Builder b = new AlertDialog.Builder(a);
                b.setTitle(title);
                b.setView(input);
                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        String t = input.getText().toString().trim();
                        if (t.length() == 0) return;
                        if (t.length() > 12) t = t.substring(0, 12);
                        try {
                            callback.onValue(Long.parseLong(t));
                        } catch (NumberFormatException ignored) {
                            // not a number: ignore
                        }
                    }
                });
                b.setNegativeButton("Cancel", null);
                b.show();
            }
        });
    }
}
