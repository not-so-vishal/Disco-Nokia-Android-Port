package javax.microedition.lcdui;

public class StringItem {
    private String label, text;

    public StringItem(String label, String text) { this.label = label; this.text = text; }
    public String getLabel() { return label; }
    public String getText() { return text; }
    public void setText(String t) { text = t; }

    @Override public String toString() {
        String l = label == null ? "" : label;
        String t = text == null ? "" : text;
        return l.isEmpty() ? t : l + " " + t;
    }
}
