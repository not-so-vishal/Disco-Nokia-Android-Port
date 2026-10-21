package javax.microedition.lcdui;

public class Alert extends Form {
    public static final int FOREVER = -2;
    private volatile int timeout = 2000;

    public Alert(String title) { super(title); }
    public void setTimeout(int millis) { timeout = millis; }
    public int getTimeout() { return timeout; }
}
