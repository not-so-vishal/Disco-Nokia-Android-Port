package javax.microedition.lcdui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.concurrent.CopyOnWriteArrayList;

/** Text screen. Rendered with themed Android widgets so long text scrolls and wraps. */
public class Form extends Displayable {
    private final CopyOnWriteArrayList<Object> items = new CopyOnWriteArrayList<>();
    private volatile boolean centered;

    public void setCentered(boolean c) { centered = c; }

    public Form(String title) { this.title = title == null ? "" : title; }

    public int append(String s) { items.add(s == null ? "" : s); changed(); return items.size() - 1; }
    public int append(StringItem s) { items.add(s); changed(); return items.size() - 1; }
    public void delete(int i) { if (i >= 0 && i < items.size()) { items.remove(i); changed(); } }
    public void deleteAll() { items.clear(); changed(); }
    public int size() { return items.size(); }
    public void setString(String s) { items.clear(); if (s != null) items.add(s); changed(); }

    @Override protected boolean rebuildOnChange() { return true; }

    private String buildText() {
        StringBuilder sb = new StringBuilder();
        for (Object o : items) {
            if (o instanceof StringItem) {
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) != '\n') sb.append('\n');
                sb.append(o.toString()).append('\n');
            } else {
                String s = String.valueOf(o);
                boolean atLineStart = sb.length() == 0 || sb.charAt(sb.length() - 1) == '\n';
                if (s.equals("\n") && atLineStart) continue; // avoid blank lines after StringItems
                sb.append(s);
            }
        }
        return sb.toString().trim();
    }

    @Override public View makeView(Context c) {
        final float dp = c.getResources().getDisplayMetrics().density;
        final boolean alert = this instanceof Alert;
        LinearLayout root = new LinearLayout(c);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Theme.BG);
        int pad = (int) (16 * dp);
        root.setPadding(pad, (int) (32 * dp), pad, (int) (20 * dp));

        String t = title;
        if (t != null && t.length() > 0) {
            TextView head = new TextView(c);
            head.setText(t);
            head.setTextColor(Theme.FG);
            head.setTextSize(22);
            head.setTypeface(Typeface.DEFAULT_BOLD);
            head.setGravity(Gravity.CENTER_HORIZONTAL);
            head.setPadding(0, 0, 0, (int) (12 * dp));
            root.addView(head, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        ScrollView sv = new ScrollView(c);
        sv.setFillViewport(true);
        TextView body = new TextView(c);
        body.setText(buildText());
        body.setTextColor(Theme.FG);
        body.setTextSize(18);
        body.setLineSpacing(0, 1.15f);
        body.setGravity(alert || centered ? Gravity.CENTER : Gravity.TOP | Gravity.START);
        sv.addView(body, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        root.addView(sv, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));

        if (alert) {
            View.OnClickListener dismiss = new View.OnClickListener() {
                @Override public void onClick(View v) { Display.getDisplay(null).dismissAlert((Alert) Form.this); }
            };
            root.setOnClickListener(dismiss);
            sv.setOnClickListener(dismiss);
            body.setOnClickListener(dismiss);
        }

        java.util.ArrayList<Command> cmds = sortedCommands();
        if (!cmds.isEmpty()) {
            View divider = new View(c);
            divider.setBackgroundColor(Theme.PANEL);
            LinearLayout.LayoutParams dlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Math.max(1, (int) dp));
            dlp.setMargins(0, (int) (14 * dp), 0, 0);
            root.addView(divider, dlp);
            LinearLayout bar = new LinearLayout(c);
            bar.setOrientation(LinearLayout.HORIZONTAL);
            bar.setPadding(0, (int) (14 * dp), 0, 0);
            for (final Command cmd : cmds) {
                Button b = new Button(c);
                b.setText(cmd.getLabel());
                b.setTextColor(Theme.FG);
                b.setBackgroundColor(Theme.HILITE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) { fire(cmd); }
                });
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                int m = (int) (4 * dp);
                lp.setMargins(m, 0, m, 0);
                bar.addView(b, lp);
            }
            root.addView(bar, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        view = root;
        return root;
    }

    @Override public void onBack() {
        if (this instanceof Alert) { Display.getDisplay(null).dismissAlert((Alert) this); return; }
        super.onBack();
    }
}
