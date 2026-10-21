package com.nokia.mid.appl.disco;

import com.nokia.mid.ui.DeviceControl;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;

/* JADX INFO: loaded from: disco_176x208_129284.jar:com/nokia/mid/appl/disco/d.class */
public class d extends e {
    private Image e;
    private Form h;
    private boolean c;
    private Displayable d;
    private int k;
    private List b;
    private int a = 0;
    private int l = 0;

    public int e() {
        return this.l;
    }

    private void d() {
        if (this.k != 1) {
            this.bq.a(this.k == 1);
            this.c = true;
            this.k = 0;
            return;
        }
        this.k = 0;
        this.b = new List(com.nokia.mid.appl.disco.c.a(111), 3);
        for (int i = 0; i <= com.nokia.mid.appl.disco.e.aY[0]; i++) {
            this.b.append(com.nokia.mid.appl.disco.c.a(41, new String[]{new StringBuffer().append("").append(i + 1).toString()}), (Image) null);
        }
        this.b.addCommand(com.nokia.mid.appl.disco.e.bK);
        this.b.setCommandListener(this);
        Display display = Display.getDisplay(this.bq);
        this.d = display.getCurrent();
        display.setCurrent(this.b);
    }

    @Override // com.nokia.mid.appl.disco.e
    public synchronized void commandAction(Command command, Displayable displayable) {
        super.commandAction(command, displayable);
        if (displayable == this.b) {
            Display display = Display.getDisplay(this.bq);
            if (command == com.nokia.mid.appl.disco.e.bK) {
                this.l = 0;
                if (this.d != null) {
                    display.setCurrent(this.d);
                } else {
                    display.setCurrent(this);
                }
                this.d = null;
            } else {
                this.l = this.b.getSelectedIndex();
            }
            this.b.removeCommand(com.nokia.mid.appl.disco.e.bK);
            this.b.setCommandListener((CommandListener) null);
            this.b = null;
            if (command != com.nokia.mid.appl.disco.e.bK) {
                this.c = true;
                this.bq.a(true);
                return;
            }
            return;
        }
        if (command == com.nokia.mid.appl.disco.e.bx) {
            return; // Exit is handled (with the confirmation) in e.commandAction
        }
        if (displayable == this.h && command == com.nokia.mid.appl.disco.e.bK) {
            Display display2 = Display.getDisplay(this.bq);
            if (this.d == this) {
                this.a = 100000;
            }
            display2.setCurrent(this.d);
            this.d = null;
            while (this.h.size() > 0) {
                this.h.delete(0);
            }
            return;
        }
        if (command == com.nokia.mid.appl.disco.e.j) {
            this.k = 1;
            d();
            return;
        }
        if (command == com.nokia.mid.appl.disco.e.aG) {
            this.k = 2;
            d();
        } else if (command != com.nokia.mid.appl.disco.e.z) {
            if (command == com.nokia.mid.appl.disco.e.i) {
                a(Display.getDisplay(this.bq).getCurrent());
            }
        } else {
            this.h.setTitle(com.nokia.mid.appl.disco.c.a(40));
            this.h.append(com.nokia.mid.appl.disco.c.a(0));
            Display display3 = Display.getDisplay(this.bq);
            this.d = display3.getCurrent();
            display3.setCurrent(this.h);
        }
    }

    private void a(Displayable displayable) {
        this.h.setTitle(com.nokia.mid.appl.disco.c.a(30));
        if (com.nokia.mid.appl.disco.e.bL != null) {
            for (int i = 0; i < 3; i++) {
                for (int i2 = 0; i2 < 1; i2++) {
                    this.h.append(new StringItem(com.nokia.mid.appl.disco.e.bL[i][i2], new StringBuffer().append("").append(com.nokia.mid.appl.disco.e.ay[i][i2]).toString()));
                }
            }
        }
        this.d = displayable;
        Display.getDisplay(this.bq).setCurrent(this.h);
    }

    @Override // com.nokia.mid.appl.disco.e
    public void b() throws Exception {
        super.b();
        try {
            this.e = Image.createImage("/title.png");
        } catch (Exception e) {
        }
        this.E = false;
        this.h = new Form("");
        this.h.addCommand(com.nokia.mid.appl.disco.e.bK);
        this.h.setCommandListener(this);
    }

    @Override // com.nokia.mid.appl.disco.e
    public void showNotify() {
        super.showNotify();
        com.example.disco.MusicPlayer.play("title.mid"); // title screen = title music
    }

    @Override // com.nokia.mid.appl.disco.e
    public void a() {
        this.k = 0;
        this.c = false;
        DeviceControl.setLights(0, 100);
        com.example.disco.MusicPlayer.play("title.mid");
        super.a();
    }

    @Override // com.nokia.mid.appl.disco.e
    public void a(int i, int i2) {
        try {
            this.a += i;
            if (this.a + i >= 5000 && this.a <= 5000) {
                i(0);
            }
            switch (this.k) {
                case 1:
                case 2:
                    break;
                default:
                    Thread.sleep(10L);
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override // com.nokia.mid.appl.disco.e
    public void a(Graphics graphics) {
        graphics.setColor(0, 0, 0);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        b(graphics);
    }

    private void b(Graphics graphics) {
        if (this.e == null) {
            return;
        }
        int width = (getWidth() - 128) / 2;
        int height = (getHeight() - 128) / 2;
        graphics.drawImage(this.e, width + ((128 - this.e.getWidth()) / 2), height + ((128 - this.e.getHeight()) / 2), 20);
        // Hint for touch players; blinks. The menu also opens by itself after 5 seconds (see a(int, int)).
        if (System.currentTimeMillis() % 1200 < 800) {
            graphics.setFont(Font.getFont(64, 1, 0));
            int hintY = height + 128 + 8;
            graphics.setColor(0, 0, 0);
            graphics.drawString("Tap to start", (getWidth() / 2) + 1, hintY + 1, 17);
            graphics.setColor(255, 255, 255);
            graphics.drawString("Tap to start", getWidth() / 2, hintY, 17);
        }
    }

    @Override // com.nokia.mid.appl.disco.e
    public void f(int i, int i2) {
        i(0);
    }

    @Override // com.nokia.mid.appl.disco.e
    public void J() {
        super.J();
        this.a = 100000;
        this.e = null;
    }

    public boolean c() {
        return this.c;
    }
}
