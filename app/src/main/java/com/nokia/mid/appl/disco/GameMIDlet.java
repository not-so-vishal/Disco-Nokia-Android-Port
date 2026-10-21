package com.nokia.mid.appl.disco;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/* JADX INFO: loaded from: disco_176x208_129284.jar:com/nokia/mid/appl/disco/GameMIDlet.class */
public class GameMIDlet extends MIDlet implements Runnable {
    private Display a;
    private b h;
    private d g;
    private boolean e;
    private Thread j;
    private boolean d = true;
    private int i = -1;
    private boolean c = false;
    private boolean b = false;
    private int f = -1;
    private boolean k = false;

    public GameMIDlet() {
        try {
            this.h = new b();
            this.h.a(this);
            this.h.b();
            this.a = Display.getDisplay(this);
            this.g = new d();
            this.g.a(this);
            this.g.b();
            this.g.setCommandListener(this.g);
            this.h.setCommandListener(this.g);
            com.nokia.mid.appl.disco.e.a(this.h.u());
        } catch (Exception e) {
            e.printStackTrace();
            notifyDestroyed();
        }
    }

    public void startApp() {
        try {
            if (this.d) {
                this.g.a();
                this.a.setCurrent(this.g);
                this.f = -1;
                this.i = 0;
            } else if (this.i == 0) {
                this.g.h();
            } else {
                this.h.h();
            }
            this.j = new Thread(this);
            this.j.start();
        } catch (Exception e) {
            e.printStackTrace();
            notifyDestroyed();
        }
    }

    public void pauseApp() {
        if (this.i == 0) {
            this.g.w();
        } else {
            this.h.w();
        }
    }

    public void destroyApp(boolean z) {
        this.c = true;
        try {
            if (this.j != null) {
                this.k = true;
                this.j.join();
            }
        } catch (Exception e) {
        }
    }

    public void a(boolean z) {
        this.e = z;
        this.f = 1;
    }

    private void b() {
        if (this.i == 0) {
            return;
        }
        this.i = 0;
        if (this.h.f() && !this.h.u()) {
            this.h.J();
            com.nokia.mid.appl.disco.e.a(false);
        } else if (this.h.o()) {
            com.nokia.mid.appl.disco.e.a(this.h.u());
        } else {
            this.h.w();
            com.nokia.mid.appl.disco.e.a(this.h.u());
        }
        this.g.a();
        this.g.i(0);
    }

    private void a() {
        switch (this.f) {
            case 0:
                b();
                break;
            case 1:
                d();
                break;
        }
        this.f = -1;
    }

    public void c() {
        this.c = true;
    }

    private void d() {
        com.nokia.mid.appl.disco.e.a(true);
        this.g.J();
        this.i = 1;
        if (this.e) {
            if (!this.h.f() && !this.d) {
                this.h.J();
            }
            this.h.repaint();
            this.h.d(this.g.e());
            this.h.a();
            this.a.setCurrent(this.h);
        } else {
            if (this.h.f()) {
                this.h.d(this.g.e());
                this.h.a();
            } else {
                this.h.h();
            }
            this.a.setCurrent(this.h);
        }
        this.d = false;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean zU = this.h.u();
        while (!this.b) {
            boolean zF = false;
            if (this.i == 0) {
                this.g.D();
                zF = this.g.c();
                if (zF && this.f == -1) {
                    this.f = 1;
                }
            } else if (this.i == 1) {
                this.h.D();
                zF = this.h.f();
                if (zF && this.f == -1) {
                    this.f = 0;
                }
            }
            if (zF || this.f != -1) {
                a();
            } else if (zU != this.h.u()) {
                zU = this.h.u();
                com.nokia.mid.appl.disco.e.a(zU);
            }
            if (this.c) {
                if (this.i == 0) {
                    this.g.J();
                } else {
                    this.h.J();
                }
                this.b = true;
            }
        }
        this.h.l();
        this.g.l();
        if (this.k) {
            return;
        }
        notifyDestroyed();
    }
}
