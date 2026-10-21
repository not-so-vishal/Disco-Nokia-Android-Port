package com.nokia.mid.appl.disco;

import java.io.DataInputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: disco_176x208_129284.jar:com/nokia/mid/appl/disco/c.class */
public class c {
    private static c a = null;
    public static final String b = System.getProperty("microedition.locale");

    private c() {
    }

    private static String a(String str, String str2, String str3) {
        int iIndexOf = str.indexOf(str2);
        return iIndexOf >= 0 ? new StringBuffer().append(str.substring(0, iIndexOf)).append(str3).append(str.substring(iIndexOf + str2.length())).toString() : str;
    }

    public static synchronized String a(int i) {
        return a(i, null);
    }

    public static synchronized String a(int i, String[] strArr) {
        try {
            if (a == null) {
                a = new c();
            }
            InputStream resourceAsStream = null;
            try {
                if (javax.microedition.midlet.MIDlet.activity() != null) {
                    java.util.Locale loc = java.util.Locale.getDefault();
                    String[] tags = { loc.getLanguage() + "-" + loc.getCountry(), loc.getLanguage() };
                    for (String tag : tags) {
                        if (resourceAsStream != null) break;
                        try { resourceAsStream = javax.microedition.midlet.MIDlet.activity().getAssets().open("disco/lang." + tag); } catch (Exception ignored) {}
                    }
                    if (resourceAsStream == null) resourceAsStream = javax.microedition.midlet.MIDlet.activity().getAssets().open("disco/lang.xx");
                }
            } catch (Exception ignored) {}
            if (resourceAsStream == null) resourceAsStream = a.getClass().getResourceAsStream("/lang.xx");
            if (resourceAsStream == null) {
                return "NoLang";
            }
            DataInputStream dataInputStream = new DataInputStream(resourceAsStream);
            dataInputStream.skipBytes(i * 2);
            dataInputStream.skipBytes((dataInputStream.readShort() - (i * 2)) - 2);
            String utf = dataInputStream.readUTF();
            dataInputStream.close();
            if (strArr != null) {
                if (strArr.length == 1) {
                    utf = a(utf, "%U", strArr[0]);
                } else {
                    for (int i2 = 0; i2 < strArr.length; i2++) {
                        utf = a(utf, new StringBuffer().append("%").append(i2).append("U").toString(), strArr[i2]);
                    }
                }
            }
            return utf;
        } catch (Exception e) {
            return "Err";
        }
    }
}
