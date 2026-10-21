package com.nokia.mid.appl.disco;

import com.nokia.mid.ui.FullCanvas;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.rms.RecordStore;

/* JADX INFO: loaded from: disco_176x208_129284.jar:com/nokia/mid/appl/disco/e.class */
public class e extends FullCanvas implements CommandListener {
    protected GameMIDlet bq;
    private int bI;
    int Z;
    static Command j;
    static Command aG;
    static Command z;
    static Command bx;
    static Command bK;
    static Command i;
    static byte[] aY = {0};
    static boolean S = false;
    private List x;
    private Vector n;
    private Command ah;
    private CommandListener g;
    private Image V;
    long f;
    int W;
    static int bl;
    static int M;
    static boolean bM;
    static String[][] bL;
    static int[][] ay;
    private boolean ao = true;
    boolean aK = false;
    boolean E = true;
    private int[] bk = new int[5];
    int aQ = 20;
    private int q = 500;
    private int aA = 20;

    public void a(GameMIDlet gameMIDlet) {
        this.bq = gameMIDlet;
    }

    public void b() throws Exception {
        this.ah = new Command(c.a(17), 2, 2);
        this.x = new List(c.a(52), 3);
        this.x.setCommandListener(this);
        this.n = new Vector();
        if (bL == null) {
            try {
                a(null, "", -1, 3, 1, true);
                for (int i2 = 0; i2 < 3; i2++) {
                    a(i2, c.a(41, new String[]{new StringBuffer().append("").append(i2 + 1).toString()}), 0);
                }
            } catch (Throwable th) {
                bL = null;
            }
        }
        if (j == null) {
            try {
                RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore("AppData", true);
                if (recordStoreOpenRecordStore.getNumRecords() == 0) {
                    recordStoreOpenRecordStore.addRecord(aY, 0, aY.length);
                } else {
                    aY = recordStoreOpenRecordStore.getRecord(1);
                }
                recordStoreOpenRecordStore.closeRecordStore();
            } catch (Exception e) {
                e.printStackTrace();
            }
            bK = new Command(c.a(17), 2, 2);
            aG = new Command(c.a(23), 1, 1);
            j = new Command(c.a(53), 1, 2);
            i = new Command(c.a(30), 1, 3);
            z = new Command(c.a(40), 1, 5);
            bx = new Command(c.a(26), 7, 8);
        }
        addCommand(j);
        addCommand(z);
        addCommand(i);
        addCommand(bx);
        if (this.V == null) {
            this.V = Image.createImage("/softkey.png");
        }
    }

    public void a(Graphics graphics) {
    }

    public void f(int i2, int i3) {
    }

    public void e(int i2, int i3) {
    }

    public void c(int i2, int i3) {
    }

    public void a() {
        r();
        this.aK = true;
        this.ao = true;
        F();
        this.f = System.currentTimeMillis();
    }

    void F() {
        this.Z = 0;
        this.bI = 0;
        for (int length = this.bk.length - 1; length >= 0; length--) {
            for (int i2 = length; i2 >= 0; i2--) {
                int[] iArr = this.bk;
                int i3 = i2;
                iArr[i3] = iArr[i3] + this.aA;
            }
        }
        this.aQ = this.aA;
        this.f = System.currentTimeMillis();
    }

    void b(int i2) {
        if (i2 > this.q) {
            i2 = this.q;
        }
        this.Z += i2;
        for (int i3 = 0; i3 < this.bk.length; i3++) {
            int[] iArr = this.bk;
            int i4 = i3;
            iArr[i4] = iArr[i4] + i2;
        }
        int length = this.bk[this.bI] / this.bk.length;
        this.bk[this.bI] = 0;
        int i5 = this.bI + 1;
        this.bI = i5;
        this.bI = i5 % this.bk.length;
        this.aQ = length;
    }

    public void w() {
        if (this.ao) {
            return;
        }
        this.ao = true;
    }

    public void h() {
        if (this.ao) {
            this.ao = false;
            this.aK = true;
            r();
            F();
            this.f = System.currentTimeMillis();
        }
    }

    public void J() {
        this.aK = false;
        this.ao = false;
    }

    public void l() {
        k();
    }

    public void k() {
        try {
            RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore("AppData", false);
            recordStoreOpenRecordStore.setRecord(1, aY, 0, aY.length);
            recordStoreOpenRecordStore.closeRecordStore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(int i2, int i3) {
    }

    public boolean o() {
        return this.ao;
    }

    public void paint(Graphics graphics) {
        if (this.aK) {
            a(graphics);
        } else {
            if (Display.getDisplay(this.bq).isColor()) {
                graphics.setColor(0, 0, 0);
            } else {
                graphics.setColor(255, 255, 255);
            }
            graphics.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private String[] touchLabels;

    /** Native touch buttons under the game: Menu, Happy hour (key 5), Game options (left soft key). */
    @Override
    protected String[] touchButtons() {
        if (!this.E) {
            return null;
        }
        if (this.touchLabels == null) {
            this.touchLabels = new String[]{"Menu", "Happy hour", c.a(28)};
        }
        return this.touchLabels;
    }

    private Form exitConfirm;
    private Command exitYes;
    private Command exitNo;

    private void confirmExit() {
        if (this.exitConfirm == null) {
            this.exitConfirm = new Form(c.a(26));
            this.exitConfirm.append("Are you sure you want to Exit?");
            this.exitConfirm.setCentered(true);
            this.exitYes = new Command("Yes", 4, 1);
            this.exitNo = new Command("No", 2, 2);
            this.exitConfirm.addCommand(this.exitYes);
            this.exitConfirm.addCommand(this.exitNo);
            this.exitConfirm.setCommandListener(this);
        }
        Display.getDisplay(this.bq).setCurrent(this.exitConfirm);
    }

    protected void keyPressed(int i2) {
        if (i2 == -7) {
            i(0);
        } else {
            f(i2, getGameAction(i2));
        }
    }

    protected void keyReleased(int i2) {
        if (i2 == -6 || i2 == -7 || i2 == -5) {
            return;
        }
        e(i2, getGameAction(i2));
    }

    protected void keyRepeated(int i2) {
        if (i2 == -6 || i2 == -7 || i2 == -5) {
            return;
        }
        c(i2, getGameAction(i2));
    }

    public void hideNotify() {
        if (!this.aK || o()) {
            return;
        }
        w();
    }

    public void showNotify() {
        if (this.aK && o()) {
            h();
        }
    }

    public void D() {
        try {
            if (!o()) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                b((int) (jCurrentTimeMillis - this.f));
                this.f = jCurrentTimeMillis;
                a(this.aQ, this.Z);
                if (Display.getDisplay(this.bq).getCurrent() == this) {
                    repaint();
                    serviceRepaints();
                }
            }
            Thread.sleep(o() ? 30L : 1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void r() {
        removeCommand(aG);
        if (S) {
            addCommand(aG);
        }
    }

    public static void a(boolean z2) {
        S = z2;
    }

    public void addCommand(Command command) {
        if (command == bx) {
            this.x.addCommand(bx);
            return;
        }
        if (this.n.contains(command)) {
            return;
        }
        int i2 = -1;
        for (int i3 = 0; i3 < this.n.size(); i3++) {
            if (((Command) this.n.elementAt(i3)).getPriority() > command.getPriority()) {
                i2 = i3;
                break;
            }
        }
        if (i2 == -1) {
            this.n.addElement(command);
        } else {
            this.n.insertElementAt(command, i2);
        }
    }

    public void removeCommand(Command command) {
        if (command == bx) {
            this.x.removeCommand(bx);
        } else {
            this.n.removeElement(command);
        }
    }

    public void setCommandListener(CommandListener commandListener) {
        this.g = commandListener;
    }

    public void i(int i2) {
        Display display = Display.getDisplay(this.bq);
        if (display.getCurrent() == this.x) {
            return;
        }
        r();
        if (this.x.size() > 0) {
            while (this.x.size() > 0) {
                this.x.delete(0);
            }
        }
        for (int i3 = 0; i3 < this.n.size(); i3++) {
            this.x.append(((Command) this.n.elementAt(i3)).getLabel(), (Image) null);
        }
        this.x.addCommand(this.ah);
        if (this.x.size() > i2) {
            this.x.setSelectedIndex(i2, true);
        }
        display.setCurrent(this.x);
    }

    public void E() {
    }

    public void commandAction(Command command, Displayable displayable) {
        if (displayable == this.exitConfirm && this.exitConfirm != null) {
            if (command == this.exitYes) {
                this.bq.c();
            } else {
                Display.getDisplay(this.bq).setCurrent(this.x);
            }
            return;
        }
        if ((displayable == this.x && command == List.SELECT_COMMAND) || command == this.ah || command == bx) {
            if (command == this.ah) {
                E();
                Display.getDisplay(this.bq).setCurrent(this);
                while (this.x.size() > 0) {
                    this.x.delete(0);
                }
                return;
            }
            if (command == bx) {
                confirmExit();
            } else {
                this.W = this.x.getSelectedIndex();
                this.g.commandAction((Command) this.n.elementAt(this.x.getSelectedIndex()), this);
            }
        }
    }

    public static void a(String str, String str2, int i2, int i3, int i4, boolean z2) throws Exception {
        RecordStore recordStoreOpenRecordStore;
        DataInputStream dataInputStream;
        boolean z3 = false;
        try {
            recordStoreOpenRecordStore = RecordStore.openRecordStore("HighScores", true);
        } catch (Exception e) {
            RecordStore.deleteRecordStore("HighScores");
            recordStoreOpenRecordStore = RecordStore.openRecordStore("HighScores", true);
            z3 = true;
        }
        if (recordStoreOpenRecordStore.getNumRecords() != 0) {
            DataInputStream dataInputStream2 = null;
            try {
                try {
                    DataInputStream dataInputStream3 = new DataInputStream(new ByteArrayInputStream(recordStoreOpenRecordStore.getRecord(1)));
                    byte b = dataInputStream3.readByte();
                    byte b2 = dataInputStream3.readByte();
                    boolean z4 = dataInputStream3.readBoolean();
                    if (b == i3 && b2 == i4 && z2 == z4) {
                        bL = new String[i3][];
                        ay = new int[i3][];
                        for (int i5 = 0; i5 < i3; i5++) {
                            bL[i5] = new String[i4];
                            ay[i5] = new int[i4];
                            for (int i6 = 0; i6 < i4; i6++) {
                                bL[i5][i6] = dataInputStream3.readUTF();
                                ay[i5][i6] = dataInputStream3.readInt();
                            }
                        }
                        dataInputStream3.close();
                        dataInputStream = null;
                    } else {
                        dataInputStream3.close();
                        dataInputStream = null;
                        recordStoreOpenRecordStore.closeRecordStore();
                        RecordStore.deleteRecordStore("HighScores");
                        recordStoreOpenRecordStore = RecordStore.openRecordStore("HighScores", true);
                        z3 = true;
                    }
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                } catch (Exception e2) {
                    recordStoreOpenRecordStore.closeRecordStore();
                    RecordStore.deleteRecordStore("HighScores");
                    recordStoreOpenRecordStore = RecordStore.openRecordStore("HighScores", true);
                    z3 = true;
                    if (0 != 0) {
                        dataInputStream2.close();
                    }
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    dataInputStream2.close();
                }
                throw th;
            }
        }
        if (recordStoreOpenRecordStore.getNumRecords() == 0 || z3) {
            bL = new String[i3][];
            ay = new int[i3][];
            for (int i7 = 0; i7 < i3; i7++) {
                bL[i7] = new String[i4];
                ay[i7] = new int[i4];
                for (int i8 = 0; i8 < i4; i8++) {
                    bL[i7][i8] = str2;
                    ay[i7][i8] = i2;
                }
            }
            byte[] bArrG = G();
            recordStoreOpenRecordStore.addRecord(bArrG, 0, bArrG.length);
        }
        recordStoreOpenRecordStore.closeRecordStore();
        bl = i3;
        M = i4;
        bM = z2;
    }

    public static void g() throws Exception {
        RecordStore recordStoreOpenRecordStore = RecordStore.openRecordStore("HighScores", true);
        byte[] bArrG = G();
        recordStoreOpenRecordStore.setRecord(1, bArrG, 0, bArrG.length);
        recordStoreOpenRecordStore.closeRecordStore();
    }

    private static byte[] G() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeByte(bl);
                dataOutputStream.writeByte(M);
                dataOutputStream.writeBoolean(bM);
                for (int i2 = 0; i2 < bl; i2++) {
                    for (int i3 = 0; i3 < M; i3++) {
                        dataOutputStream.writeUTF(bL[i2][i3]);
                        dataOutputStream.writeInt(ay[i2][i3]);
                    }
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return byteArray;
            } catch (Exception e) {
                throw e;
            }
        } catch (Throwable th) {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th;
        }
    }

    public static void a(int i2, String str, int i3) {
        if (str == null || str.equals("")) {
            str = " ";
        }
        int i4 = M - 1;
        if (bM) {
            while (i4 >= 0 && i3 > ay[i2][i4]) {
                i4--;
            }
        } else {
            while (i4 >= 0 && i3 < ay[i2][i4]) {
                i4--;
            }
        }
        int i5 = i4 + 1;
        for (int i6 = M - 2; i6 >= i5; i6--) {
            bL[i2][i6 + 1] = bL[i2][i6];
            ay[i2][i6 + 1] = ay[i2][i6];
        }
        if (i5 < M) {
            bL[i2][i5] = str;
            ay[i2][i5] = i3;
        }
        try {
            g();
        } catch (Exception e) {
        }
    }
}
