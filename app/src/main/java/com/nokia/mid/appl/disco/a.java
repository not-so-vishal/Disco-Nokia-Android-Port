package com.nokia.mid.appl.disco;

/* JADX INFO: compiled from: string */
/* JADX INFO: loaded from: disco_176x208_129284.jar:com/nokia/mid/appl/disco/a.class */
public class a {
    /** Starting money for the first challenge (whole dollars). Challenges 2 and 3 use 1500 and 1000. */
    static final int START_MONEY = 2000;
    int z;
    int y;
    int s;
    int o;
    int q;
    int m;
    int w;
    int l;
    int f;
    int u;
    int d;
    int p;
    int r;
    int k;
    boolean n;
    boolean e;
    boolean j;
    int v;
    int t;
    int c;
    int A;
    long x;
    long g;
    long h;
    int b;
    int[] i = new int[120];
    int[] a = new int[10];

    public a() {
        g(0);
    }

    public void g(int i) {
        this.e = false;
        this.j = false;
        this.A = 0;
        this.w = 0;
        this.l = 0;
        this.u = 0;
        this.d = 0;
        this.b = 0;
        this.v = 0;
        this.x = 0L;
        this.g = 0L;
        this.h = 1L;
        this.t = 0;
        for (int i2 = 0; i2 < this.a.length; i2++) {
            this.a[i2] = 0;
        }
        this.c = 0;
        switch (i) {
            case 0:
            default:
                this.z = START_MONEY << 14;
                break;
            case 1:
                this.z = 24576000;
                break;
            case 2:
                this.z = 16384000;
                break;
        }
        this.y = 49152;
        this.s = 384;
        this.o = this.s;
        this.r = 0;
        this.p = 491520;
        this.m = 0;
        this.f = 0;
        this.q = 0;
        this.k = 0;
        this.n = false;
    }

    public boolean r() {
        return this.e;
    }

    public boolean c() {
        return this.j;
    }

    public void c(int i) {
        this.q = i;
    }

    public int v() {
        return this.q;
    }

    public boolean u() {
        switch (this.q) {
            case 0:
                if (this.s > 1920) {
                    return false;
                }
            case 1:
                if (this.s > 5760) {
                    return false;
                }
            case 2:
                return this.s <= 10240;
            case 3:
            default:
                return true;
        }
    }

    public void d(int i) {
        this.j = true;
        this.z += i << 14;
        this.k += ((150 * i) / 100) << 14;
    }

    public boolean e() {
        return this.n;
    }

    public void a(boolean z) {
        this.n = z;
    }

    public boolean p() {
        return this.f != 0;
    }

    public int i() {
        return this.m;
    }

    public boolean g() {
        return this.d != 0;
    }

    public int a() {
        return this.u;
    }

    public int l() {
        return this.w / 1000;
    }

    public int j() {
        return this.l / 1000;
    }

    public void m() {
        this.t = 0;
        this.m = 0;
        this.f = 0;
        this.w = 0;
        this.u = 0;
        this.d = 0;
    }

    public boolean t() {
        return this.r - this.y > 49152;
    }

    public boolean o() {
        return this.o - this.s > 256;
    }

    public void a(int i) {
        this.v -= i;
        if (this.v < 0) {
            this.v = 0;
        } else if (this.v > 75000) {
            this.v = 75000;
        }
        if (this.m < 1 || this.t <= 0) {
            this.w += i;
        } else {
            this.w = 0;
            this.t -= i;
            if (this.t <= 0) {
                this.t = 0;
                this.m = 0;
                this.f = 0;
                this.u = 0;
                this.d = 0;
            }
        }
        this.l += i;
        this.r = 0;
        for (int i2 = 0; i2 < this.A; i2++) {
            this.r += this.i[(i2 * 3) + 2] << 7;
        }
        this.r += this.f << 7;
        this.r += this.d << 7;
        this.r += this.p;
        this.r += this.b;
        if (this.n) {
            this.r += ((160 * (this.s >> 7)) / 100) << 14;
        }
        boolean z = false;
        switch (this.q) {
            case 0:
                if (this.s > 1920) {
                    z = true;
                }
                break;
            case 1:
                if (this.s > 5760) {
                    z = true;
                }
                break;
            case 2:
                if (this.s > 10240) {
                    z = true;
                }
                break;
            case 3:
                if (this.s > 25600) {
                    z = true;
                }
                break;
        }
        if (this.r > this.y + 1920) {
            this.y += (51200 * i) / 1000;
            if (!z) {
                this.p += (1000 * i) / 1000;
            }
        } else if (this.r <= this.y - 1920) {
            this.y -= (51200 * i) / 1000;
            if (this.r > 0) {
                this.p -= (1000 * i) / 1000;
            }
        }
        if (z) {
            this.p -= (1000 * i) / 1000;
        }
        int i3 = this.s != 0 ? this.y / this.s : 0;
        this.a[this.c] = i3;
        int i4 = 0;
        for (int i5 = 0; i5 < this.a.length; i5++) {
            i4 += this.a[i5];
        }
        int length = i4 / this.a.length;
        int i6 = this.c + 1;
        this.c = i6;
        this.c = i6 % this.a.length;
        if (length - i3 <= 2 && length - i3 >= -2) {
            this.p -= (1000 * i) / 1000;
        }
        if (i3 > 160) {
            this.s += (55 * i) / 1000;
        } else if (i3 <= 112) {
            this.s -= (768 * i) / 1000;
            this.o -= (768 * i) / 1000;
            if (this.o < 0) {
                this.o = 0;
            }
            if (this.s < 0) {
                this.s = 0;
            }
        }
        if (this.o > this.s) {
            this.s += (440 * i) / 1000;
        } else {
            this.o = this.s;
        }
        if (this.s >= 38528) {
            this.s = 38528;
            this.o = this.s;
        }
        int i7 = (163840 * i) / 1000;
        if (this.k > 0) {
            this.k -= (84480 * i) / 1000;
            if (this.k < 0) {
                this.k = 0;
            }
            i7 += (84480 * i) / 1000;
        }
        this.z += (((this.n ? -48 : 153) * this.s) / 1000) * i;
        this.z -= i7;
        this.x += (long) (d() * i);
        this.g += (long) (b() * i);
        this.h += (long) i;
    }

    public void f(int i) {
        this.b = i << 7;
    }

    public int s() {
        return this.z >> 14;
    }

    public int d() {
        return this.y >> 14;
    }

    public int b() {
        return this.s >> 7;
    }

    public int q() {
        return this.k >> 14;
    }

    public boolean a(byte b, byte b2) {
        if (b >= 51 && b <= 69) {
            return b == this.m;
        }
        for (int i = 0; i < this.A * 3; i += 3) {
            if (this.i[i] == b && this.i[i + 1] >= b2) {
                return true;
            }
        }
        return false;
    }

    public void a(int i, int i2) {
        this.t = 30000;
        this.m = i;
        this.f = i2;
    }

    public void a(byte b, byte b2, int i, int i2) {
        this.z -= i << 14; // buying costs money
        this.l = 0;
        if (b >= 51 && b <= 69) {
            this.t = 30000;
            this.m = b;
            this.f = i2;
            if (b < 51 || b >= 54) {
                return;
            }
            this.f = com.nokia.mid.appl.disco.b.g(this.f / 2, this.f);
            return;
        }
        if (b < 71 || b > 99) {
            if ((b < 1 || b > 29) && (b < 31 || b > 49)) {
                return;
            }
            boolean z = false;
            for (int i3 = 0; i3 < this.A * 3; i3 += 3) {
                if (this.i[i3] == b) {
                    this.i[i3 + 1] = b2;
                    this.i[i3 + 2] = i2;
                    z = true;
                    break;
                }
            }
            if (!z) {
                this.i[this.A * 3] = b;
                this.i[(this.A * 3) + 1] = b2;
                this.i[(this.A * 3) + 2] = i2;
                this.A++;
            }
            return;
        }
        this.e = true;
        if (b == 71) {
            int i4 = 37000 - this.v;
            if (i4 < 3700) {
                i4 = 3700;
            }
            this.o += ((i4 * 40) / 37000) << 7;
            this.v += 11000;
            return;
        }
        if (b == 72) {
            int i5 = 37000 - this.v;
            if (i5 < 3700) {
                i5 = 3700;
            }
            this.o += ((i5 * 80) / 37000) << 7;
            this.v += 15000;
            return;
        }
        if (b != 73) {
            if (b == 74) {
                this.t = 30000;
                this.m = 51 + com.nokia.mid.appl.disco.b.g(3, 6);
                this.f = 1;
                this.u = com.nokia.mid.appl.disco.b.g(0, 3);
                this.d = com.nokia.mid.appl.disco.b.g(8000, 16000);
                return;
            }
            return;
        }
        int i6 = 37000 - this.v;
        if (i6 < 740) {
            i6 = 740;
        }
        int i7 = (i6 * ((40 * (this.s >> 7)) / 100)) / 37000;
        if (i7 > 20) {
            i7 = 20;
        }
        this.o += i7 << 7;
        this.v += 8000;
    }

    public int h() {
        return this.A;
    }

    public int b(int i) {
        int i2 = this.i[i * 3];
        int[] iArr = this.i;
        int i3 = (i * 3) + 1;
        iArr[i3] = iArr[i3] - 1;
        if (this.i[(i * 3) + 1] <= 0) {
            int i4 = i * 3;
            int i5 = i;
            while (i5 < this.A - 1) {
                for (int i6 = 0; i6 < 3; i6++) {
                    this.i[i4 + i6] = this.i[i4 + i6 + 3];
                }
                i5++;
                i4 += 3;
            }
            this.A--;
        }
        return i2;
    }

    public int e(int i) {
        int i2 = 0;
        int i3 = 0;
        while (i3 < this.A) {
            if (this.i[i2] == i) {
                return this.i[i2 + 1];
            }
            i3++;
            i2 += 3;
        }
        return -1;
    }

    public int k() {
        return (int) (this.h / 1000);
    }

    public int f() {
        return (int) (this.x / this.h);
    }

    public int n() {
        return (int) (this.g / this.h);
    }
}
