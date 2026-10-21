package com.nokia.mid.appl.disco;

import com.nokia.mid.ui.DirectGraphics;
import com.nokia.mid.ui.DirectUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Random;
import javax.microedition.lcdui.Alert;
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
import javax.microedition.rms.RecordStore;

/* JADX INFO: loaded from: disco_176x208_129284.jar:com/nokia/mid/appl/disco/b.class */
public class b extends e implements CommandListener {
    private boolean O;
    private int l;
    private Image N;
    private Image ba;
    private Image bm;
    private Image c;
    private Image aH;
    private int R;
    private Image aZ;
    private Image bB;
    private Image r;
    private Image Y;
    private Image[] C;
    private Font ab;
    private Font aW;
    private int bu;
    private byte[] aM;
    private String A;
    private int o;
    private byte aV;
    private byte bh;
    private int at;
    private int m;
    private int Q;
    private int a;
    private int ar;
    private byte[][] B;
    private int k;
    private int bD;
    private int X;
    private int az;
    private int bf;
    private int G;
    private int[] u;
    private boolean bd;
    private boolean aL;
    private static Random aC;
    private List bJ;
    private Alert s;
    private Form ae;
    private Display v;
    private a bs;
    private int[] T;
    private int[] bC;
    private byte[] y;
    private int[] bv;
    private int[] aq;
    private byte[] aj;
    private int[] aw;
    private int[] aD;
    private byte[] P;
    private byte[] bG;
    private int[] aT;
    private byte[] by;
    private int[] bN;
    private int ax;
    private int bA;
    private int d;
    private int Z;
    private int bt;
    private int au;
    private int aU;
    private int e;
    private int aE;
    private int K;
    private Command bF;
    private Command aJ;
    private int bn;
    private byte[] p;
    private int aF;
    private int as;
    private int aS;
    private int[] bH;
    private int al;
    private int U;
    private int[] aa;
    private int[] aB;
    private int[] F;
    private int[] ad;
    private String af;
    private String[] bE;
    private byte[] b;
    private byte[] bb;
    private byte[] h;
    private byte[] bO;
    private byte[] ai;
    private String[] bw;
    private String[] aR;
    private String[] bi;
    private String[] w;
    private byte[] be;
    private int[] bp;
    private byte[] ac;
    private int[] aI;
    private int aP;
    private int I;
    private boolean av;
    private boolean bc = false;
    private int L = 100;
    private boolean an = false;
    private boolean ag = false;
    private int aX = 256;
    private boolean ap = false;
    private boolean aO = false;
    private int[] bo = {6, 72, 20, 77, 64, 77, 77, 72};
    private int[] bj = {34, 35, 36, 37, 38, 39};
    private int[] t = {83, 93, 97, 98, 99, 100, 84, 85, 86, 87, 88, 89, 90, 91, 92, 94, 95, 96};
    private int[] bz = {82, 115, 10, 1, 18, 112};
    private int[] aN = {61, 60, 63, 62, 65, 64, 67, 66, 69, 68, 71, 70, 73, 72, 75, 74};
    private int[] H = {12, 11, 14, 13};
    private int[] am = {117, 116, 119, 118, 121, 120, 123, 122};
    private int[] bg = {3, 2, 5, 4, 7, 6, 9, 8};
    private int[] ak = {76, 77, 78, 79, 80, 81};
    private int[] br = {124, 125, 127, 126};
    private int[] D = {15, 16};
    private int[] J = {56, 57, 58};

    public void d(int i) {
        if (this.aP < i) {
            this.aP = i;
        }
        this.I = i;
    }

    @Override // com.nokia.mid.appl.disco.e
    public void b() throws Exception {
        super.b();
        aC = new Random(System.currentTimeMillis());
        C();
        this.af = com.nokia.mid.appl.disco.c.a(29);
        s();
        this.bs = new a();
        this.F = new int[12];
        for (int i = 0; i < this.F.length; i++) {
            this.F[i] = i;
        }
        for (int i2 = 0; i2 < this.F.length; i2++) {
            int i3 = this.F[i2];
            int iNextInt = aC.nextInt() % this.F.length;
            if (iNextInt < 0) {
                iNextInt = -iNextInt;
            }
            this.F[i2] = this.F[iNextInt];
            this.F[iNextInt] = i3;
        }
        this.aM = new byte[36];
        this.aa = new int[10];
        this.u = new int[80];
        this.bN = new int[10];
        this.bC = new int[this.T.length];
        this.aq = new int[this.bv.length];
        this.aD = new int[this.aw.length];
        a(this.bC, this.T, this.y, 70, 110);
        a(this.aq, this.bv, this.aj, 70, 110);
        a(this.aD, this.aw, this.bG, 90, 120);
        this.bC[6] = 0;
        this.bC[7] = 0;
        this.bC[8] = 0;
        this.v = Display.getDisplay(this.bq);
        this.s = new Alert("");
        this.s.setTitle("");
        this.bJ = new List("", 3);
        this.bJ.setCommandListener(this);
        this.ae = new Form("");
        this.ae.setCommandListener(this);
        this.bF = new Command(com.nokia.mid.appl.disco.c.a(55), 4, 1);
        this.aJ = new Command(com.nokia.mid.appl.disco.c.a(17), 2, 1);
        this.ab = Font.getFont(64, 0, 0);
        this.aW = Font.getFont(64, 0, 8);
        e();
    }

    private void a(int[] iArr, int[] iArr2, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        byte b = -1;
        int i5 = 0;
        for (int i6 = 0; i6 < iArr2.length; i6++) {
            if (bArr[i6 * 2] == b) {
                i3 = i2;
                i4 = i5;
            } else {
                i3 = 1024;
                i4 = i;
            }
            i5 = (i3 * i4) / 100;
            b = bArr[i6 * 2];
            iArr[i6] = (iArr2[i6] * i5) / 500;
        }
    }

    @Override // com.nokia.mid.appl.disco.e
    public void commandAction(Command command, Displayable displayable) {
        super.commandAction(command, displayable);
        if (displayable == this.bJ && command == List.SELECT_COMMAND && this.bN[this.ax - 1] == 1
                && this.bJ.getSelectedIndex() >= this.bz.length) {
            if (this.cheatMenu == null) {
                this.cheatMenu = new CheatMenu(this.bs, this.v, this.bJ);
            }
            this.cheatMenu.show();
            return;
        }
        if (displayable == this.bJ && command == List.SELECT_COMMAND) {
            switch (this.bN[this.ax - 1]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    j(1);
                    break;
            }
        }
        if (displayable == this.bJ || displayable == this.ae) {
            switch (this.bN[this.ax - 1]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    if (command == this.aJ) {
                        j(0);
                    }
                    break;
                case 6:
                case 13:
                    if (command == this.aJ) {
                        j(0);
                    } else if (command == this.bF) {
                        j(1);
                    }
                    break;
                case 7:
                case 15:
                    if (command == this.bF || command == this.aJ) {
                        j(0);
                    }
                    break;
                case 14:
                    if (command == this.bF) {
                        j(1);
                    }
                    break;
                case 16:
                    if (command == this.bF) {
                        j(1);
                    }
                    break;
            }
        }
    }

    private void j(int i) {
        if (i == 0) {
            p();
            if (this.bN[this.ax - 1] == 0) {
                if (this.az > 5000 && this.aF < 0) {
                    A();
                }
                this.v.setCurrent(this);
                this.aU = 1;
                return;
            }
            return;
        }
        if (i == 1) {
            switch (this.bN[this.ax - 1]) {
                case 0:
                    this.bA = 0;
                    j();
                    this.aU = 2;
                    break;
                case 1:
                    this.bA = this.bJ.getSelectedIndex();
                    this.d = 0;
                    switch (this.bJ.getSelectedIndex()) {
                        case 0:
                            n();
                            break;
                        case 1:
                            y();
                            break;
                        case 2:
                            v();
                            break;
                        case 3:
                            c();
                            break;
                        case 4:
                            x();
                            break;
                        case 5:
                            B();
                            break;
                    }
                    break;
                case 2:
                    this.d = this.bJ.getSelectedIndex();
                    if (a(this.bw, this.am, this.T, this.ai, this.y, this.bC, com.nokia.mid.appl.disco.c.a(115))) {
                        int[] iArr = this.bN;
                        int i2 = this.ax;
                        this.ax = i2 + 1;
                        iArr[i2] = 13;
                    }
                    break;
                case 3:
                    this.d = this.bJ.getSelectedIndex();
                    if (a(this.aR, this.H, this.bv, this.bO, this.aj, this.aq, com.nokia.mid.appl.disco.c.a(10))) {
                        int[] iArr2 = this.bN;
                        int i3 = this.ax;
                        this.ax = i3 + 1;
                        iArr2[i3] = 13;
                    }
                    break;
                case 4:
                    this.d = this.bJ.getSelectedIndex();
                    if (a(this.w, this.aN, this.aw, this.h, this.bG, this.aD, com.nokia.mid.appl.disco.c.a(82))) {
                        int[] iArr3 = this.bN;
                        int i4 = this.ax;
                        this.ax = i4 + 1;
                        iArr3[i4] = 13;
                    }
                    break;
                case 5:
                    this.d = this.bJ.getSelectedIndex();
                    if (a(this.bi, this.bg, this.aT, this.bb, this.by, (int[]) null, com.nokia.mid.appl.disco.c.a(1))) {
                        int[] iArr4 = this.bN;
                        int i5 = this.ax;
                        this.ax = i5 + 1;
                        iArr4[i5] = 13;
                    }
                    break;
                case 6:
                    this.bs.d(1000);
                    p();
                    this.s.setString(com.nokia.mid.appl.disco.c.a(21));
                    this.s.setTimeout(1500);
                    this.aO = true;
                    this.v.setCurrent(this.s, this.bJ);
                    break;
                case 7:
                    p();
                    break;
                case 13:
                    this.bs.a(this.aV, this.bh, this.o, this.at);
                    if (this.aV == 31) {
                        this.R = this.bh;
                    }
                    if (this.aV == 2) {
                        if (this.bh == 1) {
                            this.bs.c(1);
                        } else if (this.bh == 2) {
                            this.bs.c(2);
                        } else if (this.bh == 3) {
                            this.bs.c(3);
                        }
                    }
                    p();
                    if (this.aV >= 51 && this.aV <= 69) {
                        this.s.setString(com.nokia.mid.appl.disco.c.a(33));
                    } else if (this.aV == 74) {
                        this.s.setString(com.nokia.mid.appl.disco.c.a(this.J[this.bs.a()]));
                    } else {
                        this.s.setString(com.nokia.mid.appl.disco.c.a(101));
                    }
                    this.s.setTimeout(1500);
                    this.aO = true;
                    this.v.setCurrent(this.s, this.bJ);
                    break;
                case 14:
                    p();
                    this.aU = 1;
                    this.v.setCurrent(this);
                    break;
                case 15:
                    p();
                    this.aU = 1;
                    this.v.setCurrent(this);
                    break;
                case 16:
                    this.I++;
                    if (this.I > 2) {
                        this.bc = true;
                        this.O = false;
                    } else {
                        if (this.I > com.nokia.mid.appl.disco.e.aY[0]) {
                            com.nokia.mid.appl.disco.e.aY[0] = (byte) this.I;
                        }
                        k();
                        h(this.I, 1);
                    }
                    this.v.setCurrent(this);
                    break;
            }
        }
    }

    private void j() {
        while (this.bJ.size() > 0) {
            this.bJ.delete(0);
        }
        this.bJ.addCommand(this.aJ);
        this.bJ.setTitle(com.nokia.mid.appl.disco.c.a(28));
        if (this.bE == null) {
            this.bE = new String[this.bz.length];
            for (int i = 0; i < this.bE.length; i++) {
                this.bE[i] = com.nokia.mid.appl.disco.c.a(this.bz[i]);
            }
        }
        for (int i2 = 0; i2 < this.bE.length; i2++) {
            this.bJ.append(this.bE[i2], (Image) null);
        }
        this.bJ.append("Cheat menu", (Image) null);
        this.bJ.setSelectedIndex(this.bA, true);
        this.aO = true;
        this.v.setCurrent(this.bJ);
        int[] iArr = this.bN;
        int i3 = this.ax;
        this.ax = i3 + 1;
        iArr[i3] = 1;
        this.aU = 2;
    }

    private void y() {
        if (this.bw == null) {
            this.bw = new String[this.T.length];
            a(this.bw, this.am, this.T, this.ai, this.y, true);
        }
        a(this.bw, this.T, this.ai, this.y, com.nokia.mid.appl.disco.c.a(115));
        int[] iArr = this.bN;
        int i = this.ax;
        this.ax = i + 1;
        iArr[i] = 2;
    }

    private void v() {
        if (this.aR == null) {
            this.aR = new String[this.bv.length];
            a(this.aR, this.H, this.bv, this.bO, this.aj, true);
        }
        a(this.aR, this.bv, this.bO, this.aj, com.nokia.mid.appl.disco.c.a(10));
        int[] iArr = this.bN;
        int i = this.ax;
        this.ax = i + 1;
        iArr[i] = 3;
    }

    private void c() {
        if (this.bi == null) {
            this.bi = new String[this.aT.length];
            a(this.bi, this.bg, this.aT, this.bb, this.by, false);
        }
        a(this.bi, this.aT, this.bb, this.by, com.nokia.mid.appl.disco.c.a(1));
        int[] iArr = this.bN;
        int i = this.ax;
        this.ax = i + 1;
        iArr[i] = 5;
    }

    private void n() {
        if (this.w == null) {
            this.w = new String[this.aw.length];
            a(this.w, this.aN, this.aw, this.h, this.bG, false);
        }
        a(this.w, this.aw, this.h, this.bG, com.nokia.mid.appl.disco.c.a(82));
        int[] iArr = this.bN;
        int i = this.ax;
        this.ax = i + 1;
        iArr[i] = 4;
    }

    private void s() {
        this.bw = new String[this.T.length];
        a(this.bw, this.am, this.T, this.ai, this.y, true);
    }

    private void a(String[] strArr, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, boolean z) {
        byte b = -1;
        int i = -1;
        int i2 = 0;
        for (int i3 = 0; i3 < iArr2.length; i3++) {
            if (b != bArr2[i3 * 2]) {
                i++;
                i2 = 1;
            } else {
                i2++;
            }
            b = bArr2[i3 * 2];
            if (!z) {
                strArr[i3] = com.nokia.mid.appl.disco.c.a(iArr[i * 2], new String[]{com.nokia.mid.appl.disco.c.a(this.t[bArr[i3]])});
            } else if (i2 != 1) {
                strArr[i3] = com.nokia.mid.appl.disco.c.a(iArr[i * 2], new String[]{com.nokia.mid.appl.disco.c.a(this.bj[i2 - 1]), com.nokia.mid.appl.disco.c.a(this.t[bArr[i3]])});
            } else {
                strArr[i3] = com.nokia.mid.appl.disco.c.a(iArr[i * 2], new String[]{"", com.nokia.mid.appl.disco.c.a(this.t[bArr[i3]])});
            }
        }
    }

    private void a(String[] strArr, int[] iArr, byte[] bArr, byte[] bArr2, String str) {
        while (this.bJ.size() > 0) {
            this.bJ.delete(0);
        }
        this.bJ.addCommand(this.aJ);
        this.bJ.setTitle(str);
        int i = 0;
        int iS = this.bs.s();
        byte b = -1;
        int i2 = -1;
        int i3 = 0;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            if (b != bArr2[i4 * 2]) {
                i2++;
                i3 = 1;
            } else {
                i3++;
            }
            b = bArr2[i4 * 2];
            if (!this.bs.a(bArr2[i4 * 2], bArr2[(i4 * 2) + 1])) {
                if (iS - iArr[i4] >= 0) {
                    this.bJ.append(strArr[i4], (Image) null);
                } else if (iS - iArr[i4] >= -700) {
                    i++;
                    if (i < 3) {
                        this.bJ.append(strArr[i4], (Image) null);
                    }
                }
            }
        }
        if (this.d >= this.bJ.size()) {
            this.d = this.bJ.size() - 1;
        }
        if (this.bJ.size() > 0) {
            int i5 = this.d;
            if (i5 >= this.bJ.size()) {
                i5 = 0;
            }
            this.bJ.setSelectedIndex(i5, true);
        }
        this.aO = true;
        this.v.setCurrent(this.bJ);
    }

    private boolean a(String[] strArr, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int[] iArr3, String str) {
        int i = 0;
        int i2 = 0;
        int iS = this.bs.s();
        byte b = -1;
        int i3 = -1;
        int i4 = 0;
        for (int i5 = 0; i5 < iArr2.length; i5++) {
            if (b != bArr2[i5 * 2]) {
                i3++;
                i4 = 1;
            } else {
                i4++;
            }
            b = bArr2[i5 * 2];
            if (!this.bs.a(bArr2[i5 * 2], bArr2[(i5 * 2) + 1])) {
                if (iS - iArr2[i5] > 0) {
                    if (bArr2[i5 * 2] >= 51 && bArr2[i5 * 2] <= 69 && i2 == this.bJ.getSelectedIndex()) {
                        byte b2 = bArr2[(i5 * 2) + 1];
                        int iE = this.bs.e(31);
                        int iE2 = this.bs.e(32);
                        boolean z = false;
                        int i6 = -1;
                        int i7 = -1;
                        switch (b2) {
                            case 2:
                                i6 = 1;
                                i7 = 1;
                                break;
                            case 3:
                                i6 = 2;
                                i7 = 2;
                                break;
                            case 4:
                                i6 = 3;
                                i7 = 3;
                                break;
                        }
                        if (iE2 < i6) {
                            z = true;
                            this.s.setString(com.nokia.mid.appl.disco.c.a(80));
                        } else if (iE < i7) {
                            z = true;
                            this.s.setString(com.nokia.mid.appl.disco.c.a(81));
                        }
                        if (z) {
                            this.s.setTimeout(1500);
                            this.aO = true;
                            this.v.setCurrent(this.s, this.bJ);
                            return false;
                        }
                    }
                    if (i2 == this.bJ.getSelectedIndex()) {
                        this.A = strArr[i5];
                        this.o = iArr2[i5];
                        this.aV = bArr2[i5 * 2];
                        this.bh = bArr2[(i5 * 2) + 1];
                        if (iArr3 != null) {
                            this.at = iArr3[i5];
                        } else {
                            this.at = 0;
                        }
                        while (this.ae.size() > 0) {
                            this.ae.delete(0);
                        }
                        this.ae.setTitle(this.A);
                        this.ae.append(com.nokia.mid.appl.disco.c.a(iArr[(i3 * 2) + 1]));
                        this.ae.addCommand(this.aJ);
                        this.ae.addCommand(this.bF);
                        this.aO = true;
                        this.v.setCurrent(this.ae);
                        return true;
                    }
                    i2++;
                } else if (iS - iArr2[i5] >= -700) {
                    i++;
                    if (i >= 3) {
                        continue;
                    } else {
                        if (i2 == this.bJ.getSelectedIndex()) {
                            this.s.setString(com.nokia.mid.appl.disco.c.a(22));
                            this.s.setTimeout(1500);
                            this.aO = true;
                            this.v.setCurrent(this.s, this.bJ);
                            return false;
                        }
                        i2++;
                    }
                } else {
                    continue;
                }
            }
        }
        return true;
    }

    private void x() {
        if (this.bs.q() != 0) {
            this.s.setString(com.nokia.mid.appl.disco.c.a(20));
            this.s.setTimeout(3000);
            this.aO = true;
            this.v.setCurrent(this.s);
            return;
        }
        while (this.ae.size() > 0) {
            this.ae.delete(0);
        }
        this.ae.setTitle(com.nokia.mid.appl.disco.c.a(18));
        this.ae.append(com.nokia.mid.appl.disco.c.a(19));
        this.ae.addCommand(this.aJ);
        this.ae.addCommand(this.bF);
        this.aO = true;
        this.v.setCurrent(this.ae);
        int[] iArr = this.bN;
        int i = this.ax;
        this.ax = i + 1;
        iArr[i] = 6;
    }

    private void B() {
        while (this.ae.size() > 0) {
            this.ae.delete(0);
        }
        this.ae.setTitle(com.nokia.mid.appl.disco.c.a(112));
        int iK = ((900 - this.bs.k()) / 30) + 1;
        if (iK < 0) {
            iK = 0;
        }
        int iF = (100 * this.bs.f()) / 150;
        if (iF < 0) {
            iF = 0;
        } else if (iF > 100) {
            iF = 100;
        }
        int iS = this.bs.s() + (this.bs.n() * 10) + (this.bs.f() * 100);
        StringItem stringItem = new StringItem((String) null, com.nokia.mid.appl.disco.c.a(113, new String[]{new String(new StringBuffer().append("").append(iK).toString())}));
        StringItem stringItem2 = new StringItem((String) null, com.nokia.mid.appl.disco.c.a(50, new String[]{new String(new StringBuffer().append("").append(this.bs.s()).toString())}));
        StringItem stringItem3 = new StringItem((String) null, com.nokia.mid.appl.disco.c.a(59, new String[]{new StringBuffer().append("").append(this.bs.n() * 10).toString()}));
        StringItem stringItem4 = new StringItem((String) null, com.nokia.mid.appl.disco.c.a(51, new String[]{new StringBuffer().append("").append(iF * 100).toString()}));
        StringItem stringItem5 = new StringItem((String) null, com.nokia.mid.appl.disco.c.a(114, new String[]{new String(new StringBuffer().append("").append(iS).toString())}));
        StringItem stringItem6 = new StringItem((String) null, com.nokia.mid.appl.disco.c.a(48, new String[]{new String(new StringBuffer().append("").append(this.bs.q()).toString())}));
        this.ae.append(stringItem);
        this.ae.append("\n");
        this.ae.append(stringItem2);
        this.ae.append("\n");
        this.ae.append(stringItem3);
        this.ae.append("\n");
        this.ae.append(stringItem4);
        this.ae.append("\n");
        this.ae.append(stringItem5);
        this.ae.append("\n");
        this.ae.append(stringItem6);
        this.ae.removeCommand(this.bF);
        this.ae.addCommand(this.aJ);
        this.aO = true;
        this.v.setCurrent(this.ae);
        int[] iArr = this.bN;
        int i = this.ax;
        this.ax = i + 1;
        iArr[i] = 7;
    }

    private void p() {
        this.ax -= 2;
        switch (this.bN[this.ax]) {
            case 0:
                this.ax++;
                break;
            case 1:
                j();
                break;
            case 2:
                y();
                break;
            case 3:
                v();
                break;
            case 4:
                n();
                break;
            case 5:
                c();
                break;
        }
    }

    public boolean u() {
        return this.O;
    }

    public boolean f() {
        return this.bc;
    }

    @Override // com.nokia.mid.appl.disco.e
    public void f(int i, int i2) {
        if (this.aU == 0 || this.aU == 3 || this.aU == 4) {
            return;
        }
        switch (this.bN[this.ax - 1]) {
            case 0:
                if (i2 == 8 || i == 53) {
                    this.bs.a(!this.bs.e());
                } else if (i == -6) {
                    j(1);
                }
                break;
        }
    }

    @Override // com.nokia.mid.appl.disco.e
    public void a() {
        com.example.disco.MusicPlayer.play("title2.mid");
        this.av = false;
        h(this.I, 1);
        this.O = true;
        super.a();
    }

    @Override // com.nokia.mid.appl.disco.e
    public void h() {
        if (this.av) {
            h(this.I, 1);
        }
        super.h();
    }

    @Override // com.nokia.mid.appl.disco.e
    public void showNotify() {
        super.showNotify();
        com.example.disco.MusicPlayer.play("title2.mid"); // game screen = game music (also when resuming via Continue)
        this.an = true;
        this.aE = 0;
    }

    @Override // com.nokia.mid.appl.disco.e
    public void J() {
        z();
        this.bm = null;
        this.ba = null;
        this.c = null;
        this.aH = null;
        this.aZ = null;
        this.bB = null;
        this.r = null;
        this.Y = null;
        this.C = null;
        super.J();
    }

    @Override // com.nokia.mid.appl.disco.e
    public void l() {
    }

    private CheatMenu cheatMenu;

    @Override
    protected boolean touchButtonActive(int i) {
        return i == 1 && this.bs != null && this.bs.e();
    }

    private void g(Graphics graphics) {
        graphics.setColor((2239591 >> 16) & 255, (2239591 >> 8) & 255, 2239591 & 255);
        graphics.fillRect(-24, 11, 75, 69);
        graphics.setColor((725306 >> 16) & 255, (725306 >> 8) & 255, 725306 & 255);
        graphics.fillRect(51, 11, 101, 81);
        DirectUtils.getDirectGraphics(graphics);
        int iE = this.bs.e(1);
        if (iE > 0) {
            int i = 2;
            int i2 = 2;
            int i3 = 130;
            if (iE > 1 && iE <= 3) {
                i = 7;
                i2 = 9;
                i3 = 123;
            } else if (iE > 3) {
                i = 14;
                i2 = 15;
                i3 = 109;
            }
            int i4 = 51 - i;
            int i5 = 39 - i2;
            while (i4 > (-i)) {
                for (int i6 = i5; i6 > (-i2); i6 -= 15) {
                    graphics.setClip(i4, i6, i, i2);
                    graphics.drawImage(this.c, i4 - i3, i6, 20);
                }
                i4 -= 14;
                i5 += 7;
            }
            int i7 = 51;
            int i8 = 24;
            while (i7 < 128) {
                for (int i9 = i8; i9 > -15; i9 -= 15) {
                    graphics.setClip(i7, i9, i, i2);
                    DirectUtils.getDirectGraphics(graphics).drawImage(this.c, i7 - ((this.c.getWidth() - i3) - i), i9, 20, 8192);
                }
                i7 += 14;
                i8 += 7;
            }
        }
        int height = this.ba.getHeight();
        int height2 = this.ba.getHeight() / 2;
        int i10 = (128 - (51 - 11)) / 22;
        int i11 = (51 - 11) - ((i10 + 1) * 22);
        int i12 = 39 + ((i10 + 1) * height);
        int i13 = 0;
        int iV = this.bs.v();
        if (iV == 1) {
            iV = 2;
        } else if (iV > 1) {
            iV = (iV * 2) - 1;
        }
        while (i11 < 152) { // floor reaches the right screen edge, like the left one
            int i14 = i13 + iV;
            for (int i15 = i12; i15 < 128 + height; i15 += height) {
                graphics.setClip(i11, i15, 22, height);
                graphics.drawImage(this.ba, i11 - (i14 * 22), i15, 20);
            }
            if (iV != 2) {
                i13 = (i13 + 1) % 2;
            }
            i11 += 11 + 1;
            i12 = i11 <= 51 - 11 ? i12 - height2 : i12 + height2;
        }
    }

    private void a(String str, boolean z) {
        this.aU = 2;
        int[] iArr = this.bN;
        int i = this.ax;
        this.ax = i + 1;
        iArr[i] = 14;
        while (this.ae.size() > 0) {
            this.ae.delete(0);
        }
        this.ae.setTitle(com.nokia.mid.appl.disco.c.a(54));
        this.ae.append(str);
        this.ae.removeCommand(this.aJ);
        if (z) {
            this.ae.addCommand(this.bF);
        } else {
            this.ae.removeCommand(this.bF);
        }
        this.aO = true;
        this.v.setCurrent(this.ae);
    }

    @Override // com.nokia.mid.appl.disco.e
    public void a(int i, int i2) {
        this.aE += i;
        if (this.aE < 10) {
        }
        this.Z = i2;
        this.an = true;
        int i3 = this.aE;
        if (i3 > 800) {
            i3 = 800;
        }
        this.aE = 0;
        switch (this.aU) {
            case 0:
                this.bt++;
                if (this.bt > 0) {
                    if (this.au >= 0) {
                        if (this.I == 0) {
                            a(com.nokia.mid.appl.disco.c.a(42), false);
                        } else if (this.I == 1) {
                            a(com.nokia.mid.appl.disco.c.a(46), false);
                        } else if (this.I == 2) {
                            a(com.nokia.mid.appl.disco.c.a(44), false);
                        }
                        if (this.aR == null) {
                            this.aR = new String[this.bv.length];
                            a(this.aR, this.H, this.bv, this.bO, this.aj, true);
                        }
                        if (this.bm == null) {
                            i();
                        }
                        this.ae.addCommand(this.bF);
                    }
                    this.aE = 0;
                    F();
                    this.aU = this.e;
                }
                break;
            case 1:
                this.E = true;
                this.az += i3;
                this.X++;
                int iQ = this.bs.q();
                if (this.aU == 1) {
                    this.bs.a(i3);
                }
                if (this.bs.s() <= 0 && this.bs.e()) {
                    this.bs.a(false);
                } else if ((this.bs.s() > 0 || this.bs.e()) && 900 - this.bs.k() >= 0) {
                    int iB = this.bs.b();
                    if (iB == 0 && this.bf - this.G > 0) {
                        this.bf = 1;
                    } else if (iB < 15) {
                        int i4 = 1 + ((3 * (iB - 0)) / 15);
                        if (this.bf - this.G < i4) {
                            m();
                        } else if (this.bf - this.G > i4 && this.bf > 1) {
                            this.bf--;
                            f(this.bf);
                        }
                        if (this.bf == 0) {
                            this.bf = 1;
                        }
                    } else if (iB <= 90) {
                        int i5 = 3 + ((6 * (iB - 15)) / 75);
                        if (this.bf - this.G < i5) {
                            m();
                        } else if (this.bf - this.G > i5) {
                            this.bf--;
                            f(this.bf);
                        }
                    } else if (this.bf - this.G < 9) {
                        m();
                    } else if (this.bf - this.G > 9) {
                        this.bf--;
                        f(this.bf);
                    }
                    j(i3, i2);
                    c(i3);
                    a(i3);
                    if (iQ > 0 && this.bs.q() == 0) {
                        while (this.ae.size() > 0) {
                            this.ae.delete(0);
                        }
                        this.ae.setTitle(com.nokia.mid.appl.disco.c.a(25));
                        this.ae.append(com.nokia.mid.appl.disco.c.a(49));
                        this.ae.removeCommand(this.aJ);
                        this.ae.addCommand(this.bF);
                        this.aO = true;
                        this.v.setCurrent(this.ae);
                        int[] iArr = this.bN;
                        int i6 = this.ax;
                        this.ax = i6 + 1;
                        iArr[i6] = 15;
                        this.aU = 2;
                    } else if (this.I == 0 && this.bs.k() > 25 && !this.bs.r() && !this.aL) {
                        this.aL = true;
                        this.aS = 0;
                        while (this.ae.size() > 0) {
                            this.ae.delete(0);
                        }
                        this.ae.setTitle(com.nokia.mid.appl.disco.c.a(25));
                        this.ae.append(com.nokia.mid.appl.disco.c.a(31));
                        this.ae.removeCommand(this.aJ);
                        this.ae.addCommand(this.bF);
                        this.aO = true;
                        this.v.setCurrent(this.ae);
                        int[] iArr2 = this.bN;
                        int i7 = this.ax;
                        this.ax = i7 + 1;
                        iArr2[i7] = 15;
                        this.aU = 2;
                    } else if (this.I == 0 && this.bs.s() < 400 && !this.bs.c() && !this.bd) {
                        this.bd = true;
                        this.aS = 0;
                        while (this.ae.size() > 0) {
                            this.ae.delete(0);
                        }
                        this.ae.setTitle(com.nokia.mid.appl.disco.c.a(25));
                        this.ae.append(com.nokia.mid.appl.disco.c.a(32));
                        this.ae.removeCommand(this.aJ);
                        this.ae.addCommand(this.bF);
                        this.aO = true;
                        this.v.setCurrent(this.ae);
                        int[] iArr3 = this.bN;
                        int i8 = this.ax;
                        this.ax = i8 + 1;
                        iArr3[i8] = 15;
                        this.aU = 2;
                    } else if (this.aF < 0) {
                        this.aS += i3;
                        if (this.aS >= 10000) {
                            h(this.b[g(0, this.b.length)]);
                        }
                    } else {
                        i(this.aF, i3);
                    }
                } else {
                    this.K = 0;
                    this.aU = 3;
                }
                if (this.aU != 4) {
                    switch (this.I) {
                        case 0:
                            if (this.bs.s() >= 10000) {
                                this.bu = 0;
                                this.bs.a(false);
                                this.aU = 4;
                                this.E = false;
                                com.nokia.mid.appl.disco.e.a(this.I, com.nokia.mid.appl.disco.c.a(41, new String[]{new StringBuffer().append("").append(this.I + 1).toString()}), this.bs.s() + (this.bs.n() * 10) + (this.bs.f() * 100));
                            }
                            break;
                        case 1:
                            if ((100 * this.bs.d()) / 150 >= 100) {
                                this.bu = 0;
                                this.bs.a(false);
                                this.aU = 4;
                                this.E = false;
                                com.nokia.mid.appl.disco.e.a(this.I, com.nokia.mid.appl.disco.c.a(41, new String[]{new StringBuffer().append("").append(this.I + 1).toString()}), this.bs.s() + (this.bs.n() * 10) + (this.bs.f() * 100));
                            }
                            break;
                        case 2:
                            if (this.bs.b() >= 300) {
                                this.bu = 0;
                                this.bs.a(false);
                                this.aU = 4;
                                this.E = false;
                                com.nokia.mid.appl.disco.e.a(this.I, com.nokia.mid.appl.disco.c.a(41, new String[]{new StringBuffer().append("").append(this.I + 1).toString()}), this.bs.s() + (this.bs.n() * 10) + (this.bs.f() * 100));
                            }
                            break;
                    }
                }
                break;
            case 2:
                try {
                    Thread.sleep(100L);
                    this.aE = 0;
                    F();
                } catch (Exception e) {
                    return;
                }
                break;
            case 3:
                this.K += i3;
                if (this.K >= 4000) {
                    this.O = false;
                    this.bc = true;
                }
                break;
            case 4:
                boolean z = false;
                String strA = "";
                this.bu += i3;
                if (this.bu > 5000) {
                    switch (this.I) {
                        case 0:
                            strA = com.nokia.mid.appl.disco.c.a(43);
                            z = true;
                            break;
                        case 1:
                            strA = com.nokia.mid.appl.disco.c.a(45);
                            z = true;
                            break;
                        case 2:
                            strA = com.nokia.mid.appl.disco.c.a(47);
                            z = true;
                            break;
                    }
                }
                if (!z) {
                    this.X++;
                    j(i3, i2);
                    c(i3);
                    a(i3);
                } else {
                    while (this.ae.size() > 0) {
                        this.ae.delete(0);
                    }
                    this.ae.setTitle(com.nokia.mid.appl.disco.c.a(25));
                    this.ae.append(strA);
                    this.ae.removeCommand(this.aJ);
                    this.ae.addCommand(this.bF);
                    this.aO = true;
                    this.v.setCurrent(this.ae);
                    int[] iArr4 = this.bN;
                    int i9 = this.ax;
                    this.ax = i9 + 1;
                    iArr4[i9] = 16;
                    this.aU = 2;
                }
                break;
        }
    }

    private void d(int i, int i2) {
        this.U = g(i, b(i2, this.bf));
        for (int i3 = 0; i3 < this.U; i3++) {
            this.aa[i3] = g(0, this.U);
        }
        for (int i4 = 0; i4 < this.U; i4++) {
            for (int i5 = 0; i5 < this.U - 1; i5++) {
                if (this.aa[i5] >= this.aa[i5 + 1]) {
                    int i6 = this.aa[i5];
                    this.aa[i5] = this.aa[i5 + 1];
                    this.aa[i5 + 1] = i6;
                }
            }
        }
        for (int i7 = 0; i7 < this.U - 1; i7++) {
            if (this.aa[i7] == this.aa[i7 + 1]) {
                for (int i8 = i7; i8 < this.U - 1; i8++) {
                    this.aa[i8] = this.aa[i8 + 1];
                }
                this.U--;
            }
        }
    }

    private void q() {
        for (int i = 0; i < this.U; i++) {
            g(this.aa[i]);
        }
        this.U = 0;
    }

    private void h(int i) {
        boolean z = false;
        int iD = (100 * this.bs.d()) / 150;
        int iB = (100 * this.bs.b()) / 100;
        if (this.p[i] == 0) {
            z = false;
            i = -1;
        }
        switch (i) {
            case 0:
                if (iD > 80) {
                    d(2, 4);
                    for (int i2 = 0; i2 < this.U; i2++) {
                        int i3 = this.aa[i2] * 8;
                        this.u[i3] = 0;
                        this.u[i3 + 1] = 3;
                        this.u[i3 + 2] = 2;
                    }
                    this.bs.f(-g(4500, 5500));
                    z = true;
                }
                break;
            case 1:
                if (iB < 50) {
                    this.U = g(2, 3);
                    if (this.bf + this.U > 10) {
                        this.U = 10 - this.bf;
                    }
                    for (int i4 = 0; i4 < this.U; i4++) {
                        this.aa[i4] = this.bf;
                        int i5 = this.bf * 8;
                        this.u[i5] = 1;
                        this.u[i5 + 1] = 1;
                        this.u[i5 + 2] = 6;
                        this.u[i5 + 6] = g(70, 120);
                        this.u[i5 + 7] = g(70, 120);
                        e(this.bf);
                        this.bf++;
                    }
                    this.G += this.U;
                    this.bs.f(g(1625, 2275));
                    z = true;
                }
                break;
            case 2:
                if (iB > 80 && this.bs.s() > 1500) {
                    d(3, 4);
                    for (int i6 = 0; i6 < this.U; i6++) {
                        int i7 = this.aa[i6] * 8;
                        this.u[i7] = 0;
                        this.u[i7 + 1] = 2;
                        this.u[i7 + 2] = 7;
                    }
                    this.bs.f(-g(4500, 5500));
                    z = true;
                }
                break;
            case 3:
                if (!this.bs.p()) {
                    z = true;
                }
                break;
            case 5:
                int iE = this.bs.e(31);
                if (iB > 50 && iE < 5) {
                    this.bn = iE;
                    z = true;
                }
                break;
            case 6:
                if (!this.bs.u()) {
                    z = true;
                }
                break;
            case 7:
                if (iB > 80 || (this.bs.i() >= 51 && this.bs.i() < 54)) {
                    z = true;
                }
                break;
            case 8:
                if (iB > 30 && this.bs.p()) {
                    z = true;
                }
                break;
            case 9:
                if (this.bs.j() >= 40) {
                    this.bs.f(-7000);
                    z = true;
                }
                break;
            case 10:
                if (iD > 40 && iB < 80) {
                    this.U = 1;
                    this.aa[0] = this.bf;
                    this.G++;
                    this.bf++;
                    int i8 = this.aI[2];
                    this.bp[i8] = this.aa[0];
                    this.U = 1;
                    byte b = this.ac[i8];
                    int i9 = this.aa[0] * 8;
                    this.u[i9] = 0;
                    this.u[i9 + 1] = 13;
                    this.u[i9 + 2] = 3;
                    this.u[i9 + 3] = this.be[b * 2];
                    this.u[i9 + 4] = this.be[(b * 2) + 1];
                    this.bs.f(3250);
                    z = true;
                }
                break;
            case 11:
                if (this.bs.l() >= 20) {
                    this.bs.f(-14000);
                    z = true;
                }
                break;
        }
        if (z && this.p[i] != 0) {
            byte[] bArr = this.p;
            int i10 = i;
            bArr[i10] = (byte) (bArr[i10] - 1);
        }
        if (z) {
            this.aF = i;
            this.as = 0;
        }
        this.aS = 0;
    }

    private void i(int i, int i2) {
        boolean z = false;
        boolean z2 = false;
        String strA = "";
        this.as += i2;
        int iD = (100 * this.bs.d()) / 150;
        int iB = (100 * this.bs.b()) / 100;
        switch (i) {
            case 0:
                if (this.as - i2 <= 3000 && this.as > 3000) {
                    strA = com.nokia.mid.appl.disco.c.a(102);
                    z2 = true;
                }
                if (iD < 60 || this.as >= 30000) {
                    q();
                    this.bs.f(0);
                    z = true;
                }
                break;
            case 1:
                if (this.as - i2 <= 3000 && this.as > 3000) {
                    strA = com.nokia.mid.appl.disco.c.a(104);
                    z2 = true;
                }
                if (this.as >= 60000) {
                    this.G -= this.U;
                    q();
                    this.bs.f(0);
                    z = true;
                }
                break;
            case 2:
                if (this.as - i2 <= 3000 && this.as > 3000) {
                    strA = com.nokia.mid.appl.disco.c.a(105);
                    z2 = true;
                }
                if (this.as >= 30000) {
                    q();
                    this.bs.f(0);
                    z = true;
                }
                break;
            case 3:
                if (this.as - i2 <= 1000 && this.as > 1000) {
                    this.bs.a(58, g(2625, 3275));
                    strA = com.nokia.mid.appl.disco.c.a(106);
                    z2 = true;
                }
                if (this.as >= 60000) {
                    this.bs.f(0);
                    z = true;
                }
                break;
            case 5:
                if (this.as - i2 <= 1000 && this.as > 1000) {
                    this.bs.f(-g(2625, 3275));
                    strA = com.nokia.mid.appl.disco.c.a(107);
                    z2 = true;
                }
                if (this.bn < this.bs.e(31) || this.as > 60000) {
                    this.bs.f(0);
                    z = true;
                }
                break;
            case 6:
                if (this.as - i2 <= 1000 && this.as > 1000) {
                    this.bs.f(-g(1000, 2000));
                    strA = com.nokia.mid.appl.disco.c.a(108);
                    z2 = true;
                }
                if (this.as > 5000) {
                    this.bs.f(0);
                    z = true;
                }
                break;
            case 7:
            case 8:
                if (this.as >= 1000) {
                    if (i == 8 && this.bs.p()) {
                        int iG = g(0, 4);
                        if (iG > 0) {
                            if (this.bs.i() < 51 || this.bs.i() >= 53) {
                                iG = 0;
                            } else {
                                this.bs.m();
                            }
                        }
                        strA = com.nokia.mid.appl.disco.c.a(this.ak[iG]);
                        z2 = true;
                    } else if (i == 7 && this.bs.h() > 0) {
                        int iB2 = this.bs.b(g(0, this.bs.h()));
                        if (iB2 >= 1 && iB2 <= 29) {
                            if (iB2 == 2) {
                                int iE = this.bs.e(iB2);
                                if (iE == -1) {
                                    iE = 0;
                                }
                                this.bs.c(iE);
                            }
                            strA = com.nokia.mid.appl.disco.c.a(this.br[iB2 - 1]);
                            z2 = true;
                        } else if (iB2 >= 31 && iB2 <= 49) {
                            strA = com.nokia.mid.appl.disco.c.a(this.D[iB2 - 31]);
                            z2 = true;
                        }
                    }
                    z = true;
                }
                break;
            case 9:
                if (this.as - i2 <= 3000 && this.as > 3000) {
                    strA = com.nokia.mid.appl.disco.c.a(110);
                    z2 = true;
                }
                if (this.bs.j() < 10 || this.as > 60000) {
                    this.bs.f(0);
                    z = true;
                }
                break;
            case 10:
                if (this.as - i2 <= 3000 && this.as > 3000) {
                    strA = com.nokia.mid.appl.disco.c.a(103);
                    z2 = true;
                }
                if (iB > 100 || this.as >= 60000) {
                    this.G -= this.U;
                    q();
                    this.bs.f(0);
                    z = true;
                }
                break;
            case 11:
                if (this.as - i2 <= 1000 && this.as > 1000) {
                    strA = com.nokia.mid.appl.disco.c.a(109);
                    z2 = true;
                }
                if (this.bs.l() == 0) {
                    this.bs.f(0);
                    z = true;
                }
                break;
        }
        if (z) {
            this.aF = -1;
            this.as = 0;
        }
        this.aS = 0;
        if (z2) {
            while (this.ae.size() > 0) {
                this.ae.delete(0);
            }
            this.ae.setTitle(com.nokia.mid.appl.disco.c.a(25));
            this.ae.append(strA);
            this.ae.removeCommand(this.aJ);
            this.ae.addCommand(this.bF);
            this.aO = true;
            this.v.setCurrent(this.ae);
            int[] iArr = this.bN;
            int i3 = this.ax;
            this.ax = i3 + 1;
            iArr[i3] = 15;
            this.aU = 2;
        }
    }

    private void j(int i, int i2) {
        this.ar += i;
        if (this.ar > 15000) {
            this.ar = 0;
            int length = (this.B.length / 2) - this.m;
            if (length > 4) {
                length = 4;
            }
            int i3 = this.a + 1;
            this.a = i3;
            this.a = i3 % length;
            this.a += this.m;
        }
        int iV = 3 + this.bs.v();
        switch (this.B[this.a * 2][0]) {
            case 0:
                byte b = this.B[this.a * 2][1];
                byte[] bArr = this.B[(this.a * 2) + 1];
                int i4 = (i2 / 400) % b;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                while (i7 < iV) {
                    for (int i8 = 0; i8 < iV; i8++) {
                        this.aM[i5 + i8] = bArr[i6 + ((i8 + i4) % b)];
                    }
                    i7++;
                    i5 += iV;
                    i6 += b;
                }
                break;
            case 1:
                byte b2 = this.B[this.a * 2][1];
                byte b3 = this.B[this.a * 2][2];
                byte[] bArr2 = this.B[(this.a * 2) + 1];
                int i9 = 0;
                int i10 = ((i2 / (b3 * 10)) % b2) * 6 * 6;
                int i11 = 0;
                while (i11 < iV) {
                    for (int i12 = 0; i12 < iV; i12++) {
                        this.aM[i9 + i12] = bArr2[i10 + i12];
                    }
                    i11++;
                    i9 += iV;
                    i10 += 6;
                }
                break;
        }
    }

    @Override // com.nokia.mid.appl.disco.e
    public void a(Graphics graphics) {
        if (this.an) {
            this.an = false;
            int width = (getWidth() - 128) / 2;
            int height = (getHeight() - 128) / 2;
            if (this.aU == 0) {
                return;
            }
            if (this.aU == 3) {
                b(graphics, width, height);
                graphics.setFont(this.ab);
                graphics.setColor(0, 0, 0);
                graphics.drawString(com.nokia.mid.appl.disco.c.a(27), 63, 65, 33);
                graphics.setColor(255, 255, 255);
                graphics.drawString(com.nokia.mid.appl.disco.c.a(27), 64, 64, 33);
                return;
            }
            if (this.bN[this.ax - 1] == 0) {
                b(graphics, width, height);
                if (this.aU == 4) {
                    int i = 0;
                    int iS = this.bs.s() + (this.bs.n() * 10) + (this.bs.f() * 100);
                    switch (this.I) {
                        case 0:
                            i = (5 * iS) / 24000;
                            break;
                        case 1:
                            i = (5 * iS) / 24000;
                            break;
                        case 2:
                            i = (5 * iS) / 24000;
                            break;
                    }
                    if (i < 1) {
                        i = 1;
                    } else if (i > 5) {
                        i = 5;
                    }
                    int width2 = ((getWidth() - ((i * 13) + ((i - 1) * 6))) / 2) + 6;
                    int height2 = ((getHeight() / 2) - 3) - 7;
                    DirectUtils.getDirectGraphics(graphics).setARGBColor(-1134534560);
                    graphics.setClip(0, 0, getWidth(), getHeight());
                    graphics.fillRect(0, (((getHeight() / 2) - 15) - 3) - 2, getWidth(), 15 + this.ab.getHeight() + 6 + 4);
                    for (int i2 = 0; i2 < i; i2++) {
                        a(graphics, width2, height2, 6);
                        width2 += 19;
                    }
                    int height3 = (getHeight() / 2) + 3;
                    graphics.setClip(0, 0, getWidth(), getHeight());
                    graphics.setFont(this.ab);
                    graphics.setColor(0, 0, 0);
                    graphics.drawString(new StringBuffer().append("").append(iS).toString(), getWidth() / 2, height3, 17);
                    graphics.setColor(255, 255, 255);
                    graphics.drawString(new StringBuffer().append("").append(iS).toString(), (getWidth() / 2) - 1, height3 - 1, 17);
                }
            }
        }
    }

    private void b(Graphics graphics, int i, int i2) {
        graphics.translate(i, i2);
        graphics.setClip(-24, 0, 176, 128);
        g(graphics);
        int i3 = 51;
        int i4 = 63;
        int i5 = 15 / 2;
        int i6 = 15 / 2;
        for (int i7 = 0; i7 < 2; i7++) {
            graphics.setClip(i3, i4, 15, 15);
            graphics.drawImage(this.c, i3 - 15, i4, 20);
            i3 += 15 - 1;
            i4 -= i6;
        }
        int i8 = 51 - 15;
        int i9 = 63;
        for (int i10 = 0; i10 < 2; i10++) {
            graphics.setClip(i8, i9, 15, 15);
            graphics.drawImage(this.c, i8, i9, 20);
            i8 -= 15 - 1;
            i9 -= i6;
        }
        if (this.bs.p()) {
            int i11 = (this.bs.i() - 50) - 1;
            this.ag = true;
            this.k = 2;
            this.bD = 3;
            this.L = 0;
            this.aX = 256 + ((i11 + 1) * 64);
            this.m = 4;
            if (this.bs.g()) {
                switch (this.bs.a()) {
                    case 0:
                        this.ap = true;
                        break;
                }
            }
            byte b = this.P[i11 * 2];
            byte b2 = this.P[(i11 * 2) + 1];
            int i12 = 0;
            if (b2 == 3) {
                i12 = 2;
            } else if (b2 == 2) {
                i12 = 7;
            }
            a(graphics, 34, 61, b2, (this.Z / (this.L + 200)) % 2, i12, (this.Z / (this.L + 200)) % 2);
        } else {
            this.ag = false;
            this.ap = false;
            this.k = 4;
            this.bD = 1;
            this.L = 200;
            this.aX = 256;
            this.m = 0;
        }
        int i13 = 51;
        int i14 = 53;
        int i15 = 13 / 2;
        int i16 = 18 / 2;
        int i17 = ((this.R - 1) / 2) * 60;
        for (int i18 = 0; i18 < 3; i18++) {
            graphics.setClip(i13, i14, 13, 18);
            graphics.drawImage(this.aH, (i13 - 13) - i17, i14, 20);
            i13 += 9;
            i14 -= 5;
        }
        int i19 = 38;
        int i20 = 53;
        for (int i21 = 0; i21 < 3; i21++) {
            graphics.setClip(i19, i20, 13, 18);
            graphics.drawImage(this.aH, i19 - i17, i20, 20);
            i19 -= 9;
            i20 -= 5;
        }
        e(graphics);
        a(graphics, this.aM);
        f(graphics);
        c(graphics);
        d(graphics);
        if (this.bs.e(4) == 1) {
            a(graphics, 100, 11, (this.X / this.k) % 3 == 0);
        }
        for (int i22 = 0; i22 < this.ac.length; i22++) {
            if (this.bp[i22] != -1) {
                int i23 = this.bp[i22] * 8;
                a(graphics, this.u[i23 + 3], this.u[i23 + 4], this.u[i23 + 1], (this.Z / (this.u[i23 + 6] + this.L)) % 2, this.u[i23 + 2], (this.Z / (this.u[i23 + 7] + this.L)) % 2);
            }
        }
        int iE = this.bs.e(3);
        if (iE == 1 || iE == 2) {
            a(graphics, -2, 125, (this.Z / (this.L + 150)) % 3, (this.Z / (this.L + 200)) % 2);
        }
        if (iE == 2) {
            a(graphics, 102, 125, (this.Z / (this.L + 190)) % 3, (this.Z / (this.L + 250)) % 2);
        }
        switch (this.aF) {
            case 0:
                if (this.as < 10000) {
                    int i24 = 1 + ((10000 - this.as) / 1000);
                    for (int i25 = 0; i25 < this.U && i24 > 0; i25++) {
                        int i26 = this.aa[i25] * 8;
                        a(graphics, this.u[i26 + 3], 11, 25, this.u[i26 + 4] - 11, i24);
                    }
                }
                if ((this.X / 1) % 3 > 0) {
                    int i27 = 1 + (((this.bf * this.as) / 1000) / 10);
                    if (i27 > 10) {
                        i27 = 10;
                    }
                    a(graphics, i27, 0);
                }
                break;
            case 1:
                if ((this.X / 1) % 3 > 0) {
                    int i28 = 1 + (((this.bf * this.as) / 1000) / 10);
                    if (i28 > 4) {
                        i28 = 4;
                    }
                    a(graphics, i28, 7);
                }
                break;
            case 3:
                a(graphics, 0, 11, 128, 117, 10);
                if ((this.X / 1) % 3 > 0) {
                    int i29 = 1 + (((this.bf * this.as) / 1000) / 10);
                    if (i29 > 4) {
                        i29 = 4;
                    }
                    a(graphics, i29, 7);
                }
                break;
            case 6:
            case 9:
                if ((this.X / 1) % 3 > 0) {
                    int i30 = 1 + (((this.bf * this.as) / 1000) / 10);
                    if (i30 > 10) {
                        i30 = 10;
                    }
                    a(graphics, i30, 0);
                }
                break;
            case 10:
                if (this.as < 40000) {
                    int i31 = 1 + ((40000 - this.as) / 1000);
                    if (i31 > 3) {
                        i31 = 3;
                    }
                    for (int i32 = 0; i32 < this.U && i31 > 0; i32++) {
                        int i33 = this.aa[i32] * 8;
                        a(graphics, this.u[i33 + 3], (this.u[i33 + 4] - 19) - 26, 25, 45, i31);
                    }
                }
                if ((this.X / 1) % 3 > 0) {
                    int i34 = 1 + (((this.bf * this.as) / 1000) / 10);
                    if (i34 > 4) {
                        i34 = 4;
                    }
                    a(graphics, i34, 7);
                }
                break;
            case 11:
                if ((this.X / 1) % 3 > 0) {
                    int i35 = 1 + (((this.bf * this.as) / 1000) / 10);
                    if (i35 > 10) {
                        i35 = 10;
                    }
                    a(graphics, i35, 0);
                }
                break;
        }
        b(graphics);
        if (this.bs.g()) {
            switch (this.bs.a()) {
                case 1:
                case 2:
                    a(graphics, 0, 11, 128, 117, 15);
                    break;
            }
        }
        int width = this.C[0].getWidth();
        this.C[0].getHeight();
        // HUD bar spans the full screen width (176): left items move out by 24, right items by 24.
        graphics.setClip(-24, 0, 176, 128);
        graphics.setColor(255, 255, 255);
        graphics.fillRect(-24, 0, 176, 11);
        graphics.setColor(142, 142, 142);
        graphics.fillRect(-24, 11, 176, 1);
        graphics.setColor(142, 142, 142);
        graphics.fillRect(-20, 0, 172, 1);
        graphics.setColor(218, 218, 218);
        graphics.fillRect(-20, 1, 172, 1);
        graphics.setColor(218, 218, 218);
        graphics.fillRect(-20, 10, 172, 2);
        graphics.translate(-24, 0);
        graphics.setClip(0, 0, 4, 14);
        graphics.drawImage(this.Y, (-this.Y.getWidth()) + 4, 0, 20);
        if (!this.bs.o() || this.X % 2 == 0) {
            graphics.setClip(1, 1, 11, 14);
            graphics.drawImage(this.Y, 1 - 13, 1, 20);
        }
        graphics.translate(24, 0);
        if (!this.bs.t() || this.X % 2 == 0) {
            int i36 = 117 - (width * 3);
            graphics.translate(24, 0);
            graphics.setClip(i36, 1, 10, 10);
            graphics.drawImage(this.Y, i36 - 24, 1, 20);
            graphics.translate(-24, 0);
        }
        int i37 = (((121 - (width * 5)) - 2) / 2) - 1;
        graphics.setClip(i37, 1, 7, 12);
        graphics.drawImage(this.Y, i37 - 34, 1, 20);
        graphics.setClip(-48, 0, 224, 128);
        if (this.aU == 1 || ((this.I != 2 && this.aU == 4) || (this.I == 2 && this.aU == 4 && this.X % 2 == 0))) {
            int iB = this.bs.b();
            if (iB < 0) {
                iB = 0;
            }
            graphics.translate(-24, 0);
            a(graphics, 13, 2, this.C, width, 3, iB);
            graphics.translate(24, 0);
        }
        if (this.aU == 1 || ((this.I != 1 && this.aU == 4) || (this.I == 1 && this.aU == 4 && this.X % 2 == 0))) {
            int i38 = 127 - (width * 3);
            int iD = (100 * this.bs.d()) / 150;
            if (iD < 0) {
                iD = 0;
            } else if (iD > 100) {
                iD = 100;
            }
            graphics.translate(24, 0);
            a(graphics, i38, 2, this.C, width, 3, iD);
            graphics.translate(-24, 0);
        }
        if (this.aU == 1 || ((this.I != 0 && this.aU == 4) || (this.I == 0 && this.aU == 4 && this.X % 2 == 0))) {
            int i39 = (((121 - (width * 6)) - 2) / 2) + 7 + 1;
            int iS = this.bs.s();
            if (iS < 0) {
                iS = 0;
            }
            a(graphics, i39, 2, this.C, width, 6, iS);
        }
        graphics.setClip(0, 0, 128, 128);
        int iK = (this.bs.k() / 30) + 1;
        if (iK != this.l) {
            if (this.N == null) {
                this.N = Image.createImage(this.aW.stringWidth(com.nokia.mid.appl.disco.c.a(24, new String[]{"00"})) + 2, this.aW.getHeight() + 2);
            }
            String strA = com.nokia.mid.appl.disco.c.a(24, new String[]{new StringBuffer().append("").append(iK).toString()});
            this.l = iK;
            Graphics graphics2 = this.N.getGraphics();
            graphics2.setFont(this.aW);
            graphics2.setColor(128, 128, 128);
            graphics2.fillRect(0, 0, this.N.getWidth(), this.N.getHeight());
            graphics2.setColor(255, 255, 255);
            graphics2.fillRect(1, 1, this.N.getWidth() - 1, this.N.getHeight() - 1);
            graphics2.setColor(0, 0, 0);
            graphics2.drawString(strA, 2, 2, 20);
            this.Q = this.aW.stringWidth(strA) + 2;
        }
        graphics.drawImage(this.N, (getWidth() - this.Q) / 2, getHeight() - 1, 36); // centred between the two buttons
        if (this.bs.e()) {
            graphics.setClip(-24, 0, 176, 128);
            this.aW.stringWidth(this.af);
            DirectUtils.getDirectGraphics(graphics).setARGBColor(-1134534560);
            graphics.fillRect(-24, (64 - this.aW.getHeight()) - 2, 176, this.aW.getHeight() + 4);
            if ((this.X / 1) % 3 > 0) {
                graphics.setColor(255, 255, 255);
                graphics.setFont(this.aW);
                graphics.drawString(this.af, 64, 64, 33);
            }
        }
        graphics.translate(-i, -i2);
    }

    private void a(Graphics graphics, int i, int i2) {
        for (int length = this.ac.length - 1; length >= 0 && i > 0; length--) {
            if (this.bp[length] != -1) {
                int i3 = this.bp[length] * 8;
                int i4 = 9;
                int i5 = 11;
                if (i2 != 0) {
                    i4 = 10;
                    i5 = 10;
                }
                a(graphics, this.u[i3 + 3] + ((22 - i4) / 2), ((this.u[i3 + 4] - 19) - 26) - (i5 / 2), i2);
                i--;
            }
        }
    }

    private void b(Graphics graphics, int i, int i2, int i3) {
        int i4 = ((this.R - 1) / 2) * 60;
        int i5 = i2 - 17;
        graphics.setClip(i, i5, 17, 17);
        graphics.drawImage(this.aH, (i - 26) - i4, i5, 20);
        switch (i3) {
            case 0:
                int i6 = i5 - 5;
                graphics.setClip(i, i6, 17, 5);
                graphics.drawImage(this.aH, ((i - 26) - 17) - i4, i6, 20);
                break;
            case 1:
                int i7 = i5 - 17;
                graphics.setClip(i, i7, 17, 17);
                graphics.drawImage(this.aH, ((i - 26) - 17) - i4, i7, 20);
                break;
            case 2:
                int i8 = i5 - 12;
                graphics.setClip(i, i8, 17, 12);
                graphics.drawImage(this.aH, ((i - 26) - 17) - i4, i8 - 5, 20);
                int i9 = i8 - 17;
                graphics.setClip(i, i9, 17, 17);
                graphics.drawImage(this.aH, ((i - 26) - 17) - i4, i9, 20);
                break;
        }
    }

    private void f(Graphics graphics) {
        int i;
        int i2;
        int i3 = -1;
        int i4 = -1;
        switch (this.bs.e(31)) {
            case 1:
                i = 1;
                i2 = 1;
                break;
            case 2:
                i = 1;
                i3 = 0;
                i4 = 0;
                i2 = 1;
                break;
            case 3:
                i = 1;
                i3 = 1;
                i4 = 1;
                i2 = 1;
                break;
            case 4:
                i = 2;
                i3 = 1;
                i4 = 1;
                i2 = 2;
                break;
            case 5:
                i = 2;
                i3 = 2;
                i4 = 2;
                i2 = 2;
                break;
            default:
                i = 0;
                i2 = 0;
                break;
        }
        if (i >= 0) {
            b(graphics, this.bo[0], this.bo[1], i);
        }
        if (i2 >= 0) {
            b(graphics, this.bo[6], this.bo[7], i2);
        }
        if (i3 >= 0) {
            b(graphics, this.bo[2], this.bo[3], i3);
        }
        if (i4 >= 0) {
            b(graphics, this.bo[4], this.bo[5], i4);
        }
    }

    private void a(Graphics graphics, int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = i;
        int i7 = i2;
        int i8 = 0;
        int i9 = 0;
        switch (i3) {
            case 0:
                i4 = 41;
                i8 = 9;
                i9 = 11;
                break;
            case 1:
                i4 = 50;
                i8 = 8;
                i9 = 7;
                break;
            case 2:
                i4 = 58;
                i8 = 4;
                i9 = 7;
                break;
            case 3:
                i4 = 62;
                i8 = 7;
                i9 = 7;
                break;
            case 4:
                i4 = 69;
                i5 = 3;
                i8 = 5;
                i9 = 5;
                i6 -= 2;
                i7 -= 2;
                break;
            case 5:
                i4 = 74;
                i5 = 0;
                i8 = 9;
                i9 = 11;
                i6 -= 4;
                i7 -= 5;
                break;
            case 6:
                i4 = 0;
                i5 = 0;
                i8 = 13;
                i9 = 15;
                i6 -= 6;
                i7 -= 7;
                break;
            case 7:
                i4 = 24;
                i5 = 0;
                i8 = 10;
                i9 = 10;
                i6 -= 5;
                i7 -= 5;
                break;
        }
        graphics.setClip(i6, i7, i8, i9);
        graphics.drawImage(this.Y, i6 - i4, i7 - i5, 20);
    }

    private void a(Graphics graphics, int i, int i2, boolean z) {
        graphics.setClip(i, i2, 23, 28);
        graphics.drawImage(this.bm, i + 0, i2, 20);
        if (z) {
            int i3 = i - 4;
            int i4 = i2 + 24;
            graphics.setClip(i3, i4, 27, 14);
            graphics.drawImage(this.bm, i3 - 23, i4, 20);
        }
    }

    private void a(Graphics graphics, int i, int i2, int i3, int i4) {
        int i5 = i2 - 19;
        graphics.setClip(i, i5, 28, 19);
        graphics.drawImage(this.c, i - 30, i5, 20);
        a(graphics, i + 4, i2 - 12, 10, i3, 4, i4);
        int i6 = i + 2;
        int i7 = (i2 - 28) - 19;
        graphics.setClip(i6, i7, 24, 28);
        graphics.drawImage(this.c, i6 - 85, i7, 20);
        int i8 = i + 2;
        int i9 = (i2 - 56) - 19;
        graphics.setClip(i8, i9, 24, 28);
        graphics.drawImage(this.c, i8 - 58, i9, 20);
    }

    private void e(Graphics graphics) {
        int iE = this.bs.e(32);
        int i = 31;
        int i2 = 45;
        for (int i3 = 0; i3 < iE && i3 < 2; i3++) {
            graphics.setClip(i, i2, 16, 8);
            graphics.drawImage(this.aZ, i - 19, i2, 20);
            i -= 10;
            i2 -= 4;
        }
        if (iE < 0) {
            iE = 1;
        }
        int i4 = 41 + (10 * (iE - 1));
        int i5 = 46 - (5 * (iE - 1));
        for (int i6 = 0; i6 < iE; i6++) {
            graphics.setClip(i4, i5, 18, 12);
            graphics.drawImage(this.aZ, i4 - 0, i5, 20);
            i4 -= 10;
            i5 += 5;
        }
    }

    private void c(Graphics graphics) {
        int iE = this.bs.e(1);
        for (int i = 0; i < iE * 2; i++) {
            int i2 = this.ad[i * 2];
            int i3 = this.ad[(i * 2) + 1];
            graphics.setClip(i2, i3, 20, 12);
            graphics.drawImage(this.bm, i2 - 50, i3, 20);
        }
        int i4 = this.X;
        if (i4 % 2 == 0) {
            int i5 = (i4 / 2) * this.bD;
            for (int i6 = 0; i6 < this.bD; i6++) {
                int i7 = this.F[(i6 + i5) % this.F.length];
                if (i7 + 1 <= iE * 2) {
                    int i8 = this.ad[i7 * 2] + 1;
                    int i9 = this.ad[(i7 * 2) + 1] - 8;
                    graphics.setClip(i8, i9, 17, 19);
                    graphics.drawImage(this.bm, i8 - 70, i9, 20);
                }
            }
        }
    }

    private void a(Graphics graphics, int i, int i2, Image[] imageArr, int i3, int i4, int i5) {
        int i6 = i + ((i4 - 1) * i3);
        for (int i7 = 0; i7 < i4; i7++) {
            int i8 = i5 % 10;
            i5 /= 10;
            if (i8 == 0 && i7 != 0 && i5 == 0) {
                i8 = 10;
            }
            graphics.drawImage(imageArr[i8], i6, i2, 20);
            i6 -= i3;
        }
    }

    private void a(Graphics graphics, int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i2 - 19;
        int width = (i5 * 2 * 22) + (i6 * 22);
        int width2 = (i3 * 2 * 25) + (i4 * 25);
        if (i3 > 10) {
            width2 += 25;
        }
        DirectGraphics directGraphics = DirectUtils.getDirectGraphics(graphics);
        boolean z = false;
        if (i > 64) {
            z = true;
            width = (this.r.getWidth() - width) - 22;
            width2 = (this.bB.getWidth() - width2) - 25;
        }
        graphics.setClip(i, i7, 22, 19);
        if (i3 == 3) {
            if (z) {
                directGraphics.drawImage(this.r, i - width, i7 - 7, 20, 8192);
            } else {
                graphics.drawImage(this.r, i - width, i7 - 7, 20);
            }
        } else if (i3 == 2) {
            if (z) {
                directGraphics.drawImage(this.r, i - width, i7 - 7, 20, 8192);
            } else {
                graphics.drawImage(this.r, i - width, i7 - 7, 20);
            }
        } else if (z) {
            directGraphics.drawImage(this.r, i - width, i7, 20, 8192);
        } else {
            graphics.drawImage(this.r, i - width, i7, 20);
        }
        int i8 = i - 3;
        int i9 = i7 - 26;
        switch (i3) {
            case 0:
            case 4:
            case 6:
                i8 = z ? i8 + 3 : i8 + 0;
                i9 += 2;
                break;
            case 1:
                i8 = z ? i8 + 1 : i8 + 2;
                i9 += 5;
                break;
            case 2:
            case 3:
                i8 = z ? i8 + 2 : i8 + 1;
                i9 += 0;
                break;
            case 5:
                i8 = z ? i8 + 3 : i8 + 0;
                i9 += 0;
                break;
            case 7:
                i8 = z ? i8 + 2 : i8 + 0;
                i9 += 0;
                break;
            case 8:
                i8 = z ? i8 + 4 : i8 - 1;
                i9 += 3;
                break;
            case 9:
                i8 = z ? i8 + 4 : i8 + 0;
                i9 += 6;
                break;
            case 10:
                i8 = z ? i8 + 3 : i8 + 0;
                i9 += 3;
                break;
            case 11:
                i8 = z ? i8 + 3 : i8 + 0;
                i9++;
                break;
            case 12:
                i8 = z ? i8 + 0 : i8 + 2;
                i9 += 4;
                break;
            case 13:
                i8 = z ? i8 + 0 : i8 + 2;
                i9 += 4;
                break;
        }
        graphics.setClip(i8, i9, 25, 26);
        if (z) {
            directGraphics.drawImage(this.bB, i8 - width2, i9, 20, 8192);
        } else {
            graphics.drawImage(this.bB, i8 - width2, i9, 20);
        }
    }

    private void a(Graphics graphics, byte[] bArr) {
        int i = 41 - (((4 + 1) - 6) * 22);
        int i2 = (39 + (((4 + 1) - 2) * 12)) - 6;
        int iV = 3 + this.bs.v();
        if (iV == 3 || iV == 4) {
            i2 += 12;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < iV; i4++) {
            int i5 = i;
            int i6 = i2;
            int i7 = 0;
            while (i7 < iV) {
                graphics.setClip(i5, i6, 22, 12);
                graphics.drawImage(this.ba, i5 - (22 * ((bArr[i3] + 7) - 2)), i6, 20);
                i5 += 11 + 1;
                i6 += 6;
                i7++;
                i3++;
            }
            i -= 11 + 1;
            i2 += 6;
        }
    }

    private void m() {
        int i = this.bf;
        if (i < this.u.length / 8) {
            g(i);
            e(i);
            this.bf++;
        }
    }

    private void f(int i) {
        for (int i2 = 0; i2 < this.bp.length; i2++) {
            if (this.bp[i2] == i) {
                this.bp[i2] = -1;
                return;
            }
        }
    }

    private void e(int i) {
        f(i);
        int i2 = i * 8;
        switch (this.bs.v()) {
        }
        int i3 = 0;
        for (int i4 = 0; i4 < 11 && this.bp[this.aI[i3]] != -1; i4++) {
            i3 = (i3 + 1) % 11;
        }
        int i5 = this.aI[i3];
        this.bp[i5] = i;
        byte b = this.ac[i5];
        this.u[i2 + 3] = this.be[b * 2];
        this.u[i2 + 4] = this.be[(b * 2) + 1];
    }

    private void g(int i) {
        int i2 = i * 8;
        this.u[i2] = g(0, 2);
        this.u[i2 + 1] = g(0, 3);
        if (this.u[i2] != 0) {
            switch (this.u[i2 + 1]) {
                case 0:
                case 1:
                    this.u[i2 + 1] = 8;
                    break;
                case 2:
                    this.u[i2 + 1] = 9;
                    break;
            }
        } else {
            switch (this.u[i2 + 1]) {
                case 0:
                    this.u[i2 + 1] = 11;
                    break;
                case 1:
                    this.u[i2 + 1] = 12;
                    break;
                case 2:
                    this.u[i2 + 1] = 0;
                    break;
            }
        }
        switch (this.u[i2 + 1]) {
            case 1:
                this.u[i2 + 2] = 6;
                break;
            case 2:
            case 4:
            case 5:
            case 6:
            case 11:
            case 12:
            default:
                this.u[i2 + 2] = g(0, 2);
                break;
            case 3:
                this.u[i2 + 2] = 2;
                break;
            case 7:
                this.u[i2 + 2] = 4;
                break;
            case 8:
                this.u[i2 + 2] = 5;
                break;
            case 9:
                this.u[i2 + 2] = 4;
                break;
            case 10:
                this.u[i2 + 2] = 4;
                break;
            case 13:
                this.u[i2 + 2] = 3;
                break;
        }
        this.u[i2 + 5] = g(0, 2);
        this.u[i2 + 6] = g(100, 200);
        this.u[i2 + 7] = g(100, 200);
    }

    private void A() {
        for (int i = 0; i < 2; i++) {
            g((this.al + i) % (this.u.length / 8));
        }
        this.al += 2;
    }

    private void h(int i, int i2) {
        this.az = 0;
        this.bf = 0;
        for (int i3 = 0; i3 < 10; i3++) {
            int i4 = i3 * 8;
            g(i3);
        }
        for (int i5 = 0; i5 < this.bp.length; i5++) {
            this.bp[i5] = -1;
        }
        H();
        t();
        this.l = -1;
        this.bs.g(i);
        this.aF = -1;
        this.as = 0;
        this.aS = 0;
        this.G = 0;
        this.ax = 0;
        int[] iArr = this.bN;
        int i6 = this.ax;
        this.ax = i6 + 1;
        iArr[i6] = 0;
        this.m = 0;
        this.a = 0;
        this.bD = 1;
        this.k = 4;
        this.bc = false;
        this.O = true;
        this.an = true;
        this.Z = 0;
        this.bA = 0;
        this.d = 0;
        this.bd = false;
        this.aL = false;
        this.aU = 0;
        this.bt = 0;
        this.e = i2;
        this.au = i;
        this.aE = 0;
        if (this.av) {
            I();
            this.av = false;
        }
    }

    private void i() {
        try {
            this.bm = Image.createImage("/light.png");
            this.ba = Image.createImage("/floor.png");
            this.c = Image.createImage("/stage.png");
            this.bB = Image.createImage("/body.png");
            this.r = Image.createImage("/legs.png");
            this.Y = Image.createImage("/symbols.png");
            this.aZ = Image.createImage("/player.png");
            this.aH = Image.createImage("/table.png");
            this.R = 1;
            this.C = d();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void t() {
        if (this.bH == null) {
            this.bH = new int[60];
        }
        for (int i = 0; i < this.bH.length; i += 5) {
            if (i < 4) {
                this.bH[i + 1] = g(0, 42) << 4;
            } else {
                this.bH[i + 1] = this.bH[(i % 4) + 1] + (((i / 4) * 128) / 3);
            }
            this.bH[i + 3] = g(80, 120);
            this.bH[i + 2] = 0;
            this.bH[i + 4] = i % 4;
        }
    }

    private void a(int i) {
        int iE = this.bs.e(31);
        for (int i2 = 0; i2 < this.bH.length; i2 += 5) {
            if (this.bH[i2 + 4] < 2) {
                int[] iArr = this.bH;
                int i3 = i2;
                iArr[i3] = iArr[i3] + (((i * this.bH[i2 + 3]) / 1000) / 2);
            } else {
                int[] iArr2 = this.bH;
                int i4 = i2;
                iArr2[i4] = iArr2[i4] - (((i * this.bH[i2 + 3]) / 1000) / 2);
            }
            int[] iArr3 = this.bH;
            int i5 = i2 + 1;
            iArr3[i5] = iArr3[i5] - ((i * this.bH[i2 + 3]) / 1000);
            if ((this.bH[i2 + 1] >> 4) < 7) {
                this.bH[i2 + 3] = (this.aX * g(80, 120)) >> 8;
                this.bH[i2] = (this.bo[this.bH[i2 + 4] * 2] + 6) << 4;
                this.bH[i2 + 1] = (this.bo[(this.bH[i2 + 4] * 2) + 1] - 7) << 4;
                int i6 = this.bH[i2 + 4];
                if (this.ag && (i6 == 0 || i6 == 3 || iE >= 3)) {
                    this.bH[i2 + 2] = g(1, 3);
                } else {
                    this.bH[i2 + 2] = 0;
                }
            }
        }
    }

    private void d(Graphics graphics) {
        if (this.bH != null) {
            for (int i = 0; i < this.bH.length; i += 5) {
                if (this.bH[i + 2] >= 1) {
                    a(graphics, this.bH[i] >> 4, this.bH[i + 1] >> 4, this.bH[i + 2]);
                }
            }
        }
    }

    private void H() {
        if (this.aB == null) {
            this.aB = new int[60];
        }
        int iG = 10;
        for (int i = 0; i < this.aB.length; i += 4) {
            this.aB[i] = iG << 4;
            this.aB[i + 1] = g(0, 128) << 4;
            this.aB[i + 3] = g(112, 134);
            this.aB[i + 2] = 0;
            iG += 7 + g(-5, 5);
        }
    }

    private void c(int i) {
        for (int i2 = 0; i2 < this.aB.length; i2 += 4) {
            int[] iArr = this.aB;
            int i3 = i2 + 1;
            iArr[i3] = iArr[i3] + ((i * this.aB[i2 + 3]) / 1000);
            if (this.aB[i2 + 2] > 0) {
                int[] iArr2 = this.aB;
                int i4 = i2 + 2;
                iArr2[i4] = iArr2[i4] + 1;
                if (this.aB[i2 + 2] < 10) {
                    int[] iArr3 = this.aB;
                    int i5 = i2 + 3;
                    iArr3[i5] = iArr3[i5] - 5;
                } else if (this.aB[i2 + 2] < 20) {
                    int[] iArr4 = this.aB;
                    int i6 = i2 + 3;
                    iArr4[i6] = iArr4[i6] + 5;
                } else {
                    this.aB[i2 + 2] = 1;
                }
            }
            if ((this.aB[i2 + 1] >> 4) > 128) {
                this.aB[i2 + 1] = -7;
                this.aB[i2 + 3] = g(112, 134);
                if (this.ap) {
                    this.aB[i2 + 2] = 1;
                } else {
                    this.aB[i2 + 2] = 0;
                }
            }
        }
    }

    private void b(Graphics graphics) {
        if (this.aB != null) {
            for (int i = 0; i < this.aB.length; i += 4) {
                if (this.aB[i + 2] >= 1) {
                    a(graphics, this.aB[i] >> 4, this.aB[i + 1] >> 4, 3);
                }
            }
        }
    }

    private int b(int i, int i2) {
        return i < i2 ? i : i2;
    }

    static int g(int i, int i2) {
        int iNextInt = aC.nextInt();
        if (iNextInt < 0) {
            iNextInt = -iNextInt;
        }
        return i + (i2 <= i ? 0 : iNextInt % (i2 - i));
    }

    public Image[] a(Image image, int i, int i2, int i3, int i4) {
        Image[] imageArr = new Image[i2];
        int i5 = 0;
        while (i5 < i2) {
            imageArr[i5] = Image.createImage(i3, i4);
            imageArr[i5].getGraphics().drawImage(image, i % image.getWidth(), (i / image.getWidth()) * i4, 20);
            i5++;
            i -= i3;
        }
        return imageArr;
    }

    public Image[] d() throws Exception {
        Image imageCreateImage = Image.createImage("/numbers.png");
        return a(imageCreateImage, 0, 11, 5, imageCreateImage.getHeight());
    }

    private void C() {
        DataInputStream dataInputStream = null;
        try {
            try {
                dataInputStream = new DataInputStream(javax.microedition.midlet.MIDlet.activity().getAssets().open("disco/data.bin"));
                byte b = dataInputStream.readByte();
                this.B = new byte[b * 2][];
                for (int i = 0; i < b; i++) {
                    this.B[i * 2] = new byte[3];
                    for (int i2 = 0; i2 < this.B[i * 2].length; i2++) {
                        this.B[i * 2][i2] = dataInputStream.readByte();
                    }
                    int i3 = dataInputStream.readShort();
                    this.B[(i * 2) + 1] = new byte[i3];
                    byte[] bArr = this.B[(i * 2) + 1];
                    int i4 = 0;
                    while (i3 > 0) {
                        int i5 = dataInputStream.readInt();
                        int i6 = 0;
                        while (i6 < 32 && i3 > 0) {
                            bArr[i4] = (byte) (2 + ((i5 >> i6) & 1));
                            i6++;
                            i3--;
                            i4++;
                        }
                    }
                }
                int i7 = dataInputStream.readByte();
                this.T = new int[i7];
                this.y = new byte[i7 * 2];
                a(dataInputStream, this.T, this.y);
                int i8 = dataInputStream.readByte();
                this.bv = new int[i8];
                this.aj = new byte[i8 * 2];
                a(dataInputStream, this.bv, this.aj);
                int i9 = dataInputStream.readByte();
                this.aw = new int[i9];
                this.bG = new byte[i9 * 2];
                a(dataInputStream, this.aw, this.bG);
                int i10 = dataInputStream.readByte();
                this.aT = new int[i10];
                this.by = new byte[i10 * 2];
                a(dataInputStream, this.aT, this.by);
                this.P = new byte[dataInputStream.readByte()];
                for (int i11 = 0; i11 < this.P.length; i11++) {
                    this.P[i11] = dataInputStream.readByte();
                }
                int i12 = dataInputStream.readByte();
                this.ad = new int[i12];
                for (int i13 = 0; i13 < i12; i13 += 2) {
                    int i14 = (i13 / 2) % 2;
                    if (i13 >= 18 || i14 != 0) {
                        this.ad[i13] = 53 + (dataInputStream.readByte() * 14);
                        this.ad[i13 + 1] = 11 + (dataInputStream.readByte() * 12);
                    } else {
                        this.ad[i13] = (-5) + (dataInputStream.readByte() * 14);
                        this.ad[i13 + 1] = 11 + (dataInputStream.readByte() * 12);
                    }
                }
                int i15 = dataInputStream.readByte();
                this.bp = new int[i15 / 2];
                for (int i16 = 0; i16 < this.bp.length; i16++) {
                    this.bp[i16] = -1;
                }
                byte[] bArr2 = new byte[i15];
                this.aI = new int[i15 / 2];
                this.ac = new byte[i15 / 2];
                this.be = new byte[i15];
                for (int i17 = 0; i17 < this.be.length; i17++) {
                    this.be[i17] = dataInputStream.readByte();
                    bArr2[i17] = this.be[i17];
                }
                for (int i18 = 0; i18 < this.ac.length; i18++) {
                    this.ac[i18] = (byte) i18;
                }
                for (int i19 = 0; i19 < this.ac.length; i19++) {
                    for (int i20 = i19 + 1; i20 < this.ac.length; i20++) {
                        if (bArr2[(i20 * 2) + 1] < bArr2[(i19 * 2) + 1]) {
                            byte b2 = bArr2[i20 * 2];
                            bArr2[i20 * 2] = bArr2[i19 * 2];
                            bArr2[i19 * 2] = b2;
                            byte b3 = bArr2[(i20 * 2) + 1];
                            bArr2[(i20 * 2) + 1] = bArr2[(i19 * 2) + 1];
                            bArr2[(i19 * 2) + 1] = b3;
                            byte b4 = this.ac[i20];
                            this.ac[i20] = this.ac[i19];
                            this.ac[i19] = b4;
                        }
                    }
                }
                for (int i21 = 0; i21 < this.aI.length; i21++) {
                    for (int i22 = 0; i22 < this.aI.length; i22++) {
                        if (this.be[i21 * 2] == bArr2[i22 * 2] && this.be[(i21 * 2) + 1] == bArr2[(i22 * 2) + 1]) {
                            this.aI[i21] = i22;
                            break;
                        }
                    }
                }
                this.b = a(dataInputStream);
                this.bb = a(dataInputStream);
                this.h = a(dataInputStream);
                this.bO = a(dataInputStream);
                this.ai = a(dataInputStream);
                this.p = a(dataInputStream);
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (Exception e) {
                    }
                }
            } catch (Throwable th) {
                if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (Exception e2) {
                        throw th;
                    }
                }
                throw th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            if (dataInputStream != null) {
                try {
                    dataInputStream.close();
                } catch (Exception e4) {
                }
            }
        }
    }

    public byte[] a(DataInputStream dataInputStream) throws Exception {
        byte[] bArr = new byte[dataInputStream.readByte()];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = dataInputStream.readByte();
        }
        return bArr;
    }

    public void a(DataInputStream dataInputStream, int[] iArr, byte[] bArr) throws Exception {
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = dataInputStream.readByte() * 100;
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = dataInputStream.readByte();
        }
    }

    public void a(Graphics graphics, int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 / 2;
        int i7 = i5 - i6;
        for (int i8 = 0; i8 < i6; i8++) {
            a(graphics, g(i, i + i3), g(i2, i2 + i4), 4);
        }
        for (int i9 = 0; i9 < i7; i9++) {
            a(graphics, g(i, i + i3), g(i2, i2 + i4), 5);
        }
    }

    @Override // com.nokia.mid.appl.disco.e
    public void hideNotify() {
        boolean z = !this.aO;
        this.aO = false;
        super.hideNotify();
        if (z) {
            i(0);
        }
    }

    public void e() {
        try {
            RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore("DISCO", true);
            if (recordStoreOpenRecordStore.getNumRecords() != 0) {
                DataInputStream dataInputStream = null;
                try {
                    try {
                        dataInputStream = new DataInputStream(new ByteArrayInputStream(recordStoreOpenRecordStore.getRecord(1)));
                        this.O = dataInputStream.readBoolean();
                        this.av = false;
                        if (this.O) {
                            this.av = true;
                        }
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                    } catch (Throwable th) {
                        if (0 != 0) {
                            dataInputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                }
            }
            recordStoreOpenRecordStore.closeRecordStore();
        } catch (Exception e2) {
        }
    }

    public void I() {
        try {
            RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore("DISCO", true);
            if (recordStoreOpenRecordStore.getNumRecords() != 0) {
                DataInputStream dataInputStream = null;
                try {
                    try {
                        dataInputStream = new DataInputStream(new ByteArrayInputStream(recordStoreOpenRecordStore.getRecord(1)));
                        if (dataInputStream.readBoolean()) {
                            this.O = true;
                            this.bu = dataInputStream.readInt();
                            this.o = dataInputStream.readInt();
                            this.at = dataInputStream.readInt();
                            this.m = dataInputStream.readInt();
                            this.a = dataInputStream.readInt();
                            this.ar = dataInputStream.readInt();
                            this.k = dataInputStream.readInt();
                            this.bD = dataInputStream.readInt();
                            this.L = dataInputStream.readInt();
                            this.bf = dataInputStream.readInt();
                            this.G = dataInputStream.readInt();
                            this.K = dataInputStream.readInt();
                            this.bn = dataInputStream.readInt();
                            this.aF = dataInputStream.readInt();
                            this.as = dataInputStream.readInt();
                            this.aS = dataInputStream.readInt();
                            this.aX = dataInputStream.readInt();
                            this.al = dataInputStream.readInt();
                            this.U = dataInputStream.readInt();
                            this.aP = dataInputStream.readInt();
                            this.I = dataInputStream.readInt();
                            for (int i = 0; i < this.aa.length; i++) {
                                this.aa[i] = dataInputStream.readInt();
                            }
                            for (int i2 = 0; i2 < this.aB.length; i2++) {
                                this.aB[i2] = dataInputStream.readInt();
                            }
                            for (int i3 = 0; i3 < this.bp.length; i3++) {
                                this.bp[i3] = dataInputStream.readInt();
                            }
                            for (int i4 = 0; i4 < this.bH.length; i4++) {
                                this.bH[i4] = dataInputStream.readInt();
                            }
                            for (int i5 = 0; i5 < this.u.length; i5++) {
                                this.u[i5] = dataInputStream.readInt();
                            }
                            for (int i6 = 0; i6 < this.p.length; i6++) {
                                this.p[i6] = dataInputStream.readByte();
                            }
                            this.ap = dataInputStream.readBoolean();
                            this.aO = dataInputStream.readBoolean();
                            this.ag = dataInputStream.readBoolean();
                            this.aL = dataInputStream.readBoolean();
                            this.bd = dataInputStream.readBoolean();
                            this.bs.z = dataInputStream.readInt();
                            this.bs.y = dataInputStream.readInt();
                            this.bs.s = dataInputStream.readInt();
                            this.bs.o = dataInputStream.readInt();
                            this.bs.q = dataInputStream.readInt();
                            this.bs.m = dataInputStream.readInt();
                            this.bs.w = dataInputStream.readInt();
                            this.bs.l = dataInputStream.readInt();
                            this.bs.f = dataInputStream.readInt();
                            this.bs.u = dataInputStream.readInt();
                            this.bs.d = dataInputStream.readInt();
                            this.bs.p = dataInputStream.readInt();
                            this.bs.r = dataInputStream.readInt();
                            this.bs.k = dataInputStream.readInt();
                            this.bs.v = dataInputStream.readInt();
                            this.bs.t = dataInputStream.readInt();
                            this.bs.c = dataInputStream.readInt();
                            this.bs.A = dataInputStream.readInt();
                            this.bs.b = dataInputStream.readInt();
                            for (int i7 = 0; i7 < this.bs.a.length; i7++) {
                                this.bs.a[i7] = dataInputStream.readInt();
                            }
                            for (int i8 = 0; i8 < this.bs.i.length; i8++) {
                                this.bs.i[i8] = dataInputStream.readInt();
                            }
                            this.bs.n = dataInputStream.readBoolean();
                            this.bs.e = dataInputStream.readBoolean();
                            this.bs.j = dataInputStream.readBoolean();
                            this.bs.x = dataInputStream.readLong();
                            this.bs.g = dataInputStream.readLong();
                            this.bs.h = dataInputStream.readLong();
                        }
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                    } catch (Throwable th) {
                        if (0 != 0) {
                            dataInputStream.close();
                        }
                        throw th;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                }
            }
            recordStoreOpenRecordStore.closeRecordStore();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void z() {
        try {
            RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore("DISCO", true);
            byte[] byteArray = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                    dataOutputStream.writeBoolean(this.O);
                    if (this.O) {
                        dataOutputStream.writeInt(this.bu);
                        dataOutputStream.writeInt(this.o);
                        dataOutputStream.writeInt(this.at);
                        dataOutputStream.writeInt(this.m);
                        dataOutputStream.writeInt(this.a);
                        dataOutputStream.writeInt(this.ar);
                        dataOutputStream.writeInt(this.k);
                        dataOutputStream.writeInt(this.bD);
                        dataOutputStream.writeInt(this.L);
                        dataOutputStream.writeInt(this.bf);
                        dataOutputStream.writeInt(this.G);
                        dataOutputStream.writeInt(this.K);
                        dataOutputStream.writeInt(this.bn);
                        dataOutputStream.writeInt(this.aF);
                        dataOutputStream.writeInt(this.as);
                        dataOutputStream.writeInt(this.aS);
                        dataOutputStream.writeInt(this.aX);
                        dataOutputStream.writeInt(this.al);
                        dataOutputStream.writeInt(this.U);
                        dataOutputStream.writeInt(this.aP);
                        dataOutputStream.writeInt(this.I);
                        for (int i = 0; i < this.aa.length; i++) {
                            dataOutputStream.writeInt(this.aa[i]);
                        }
                        for (int i2 = 0; i2 < this.aB.length; i2++) {
                            dataOutputStream.writeInt(this.aB[i2]);
                        }
                        for (int i3 = 0; i3 < this.bp.length; i3++) {
                            dataOutputStream.writeInt(this.bp[i3]);
                        }
                        for (int i4 = 0; i4 < this.bH.length; i4++) {
                            dataOutputStream.writeInt(this.bH[i4]);
                        }
                        for (int i5 = 0; i5 < this.u.length; i5++) {
                            dataOutputStream.writeInt(this.u[i5]);
                        }
                        for (int i6 = 0; i6 < this.p.length; i6++) {
                            dataOutputStream.writeByte(this.p[i6]);
                        }
                        dataOutputStream.writeBoolean(this.ap);
                        dataOutputStream.writeBoolean(this.aO);
                        dataOutputStream.writeBoolean(this.ag);
                        dataOutputStream.writeBoolean(this.aL);
                        dataOutputStream.writeBoolean(this.bd);
                        dataOutputStream.writeInt(this.bs.z);
                        dataOutputStream.writeInt(this.bs.y);
                        dataOutputStream.writeInt(this.bs.s);
                        dataOutputStream.writeInt(this.bs.o);
                        dataOutputStream.writeInt(this.bs.q);
                        dataOutputStream.writeInt(this.bs.m);
                        dataOutputStream.writeInt(this.bs.w);
                        dataOutputStream.writeInt(this.bs.l);
                        dataOutputStream.writeInt(this.bs.f);
                        dataOutputStream.writeInt(this.bs.u);
                        dataOutputStream.writeInt(this.bs.d);
                        dataOutputStream.writeInt(this.bs.p);
                        dataOutputStream.writeInt(this.bs.r);
                        dataOutputStream.writeInt(this.bs.k);
                        dataOutputStream.writeInt(this.bs.v);
                        dataOutputStream.writeInt(this.bs.t);
                        dataOutputStream.writeInt(this.bs.c);
                        dataOutputStream.writeInt(this.bs.A);
                        dataOutputStream.writeInt(this.bs.b);
                        for (int i7 = 0; i7 < this.bs.a.length; i7++) {
                            dataOutputStream.writeInt(this.bs.a[i7]);
                        }
                        for (int i8 = 0; i8 < this.bs.i.length; i8++) {
                            dataOutputStream.writeInt(this.bs.i[i8]);
                        }
                        dataOutputStream.writeBoolean(this.bs.n);
                        dataOutputStream.writeBoolean(this.bs.e);
                        dataOutputStream.writeBoolean(this.bs.j);
                        dataOutputStream.writeLong(this.bs.x);
                        dataOutputStream.writeLong(this.bs.g);
                        dataOutputStream.writeLong(this.bs.h);
                    }
                    byteArray = byteArrayOutputStream.toByteArray();
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                }
                if (recordStoreOpenRecordStore.getNumRecords() == 0) {
                    recordStoreOpenRecordStore.addRecord(byteArray, 0, byteArray.length);
                } else {
                    recordStoreOpenRecordStore.setRecord(1, byteArray, 0, byteArray.length);
                }
                recordStoreOpenRecordStore.closeRecordStore();
            } catch (Throwable th) {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
