package javax.microedition.rms;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.microedition.midlet.MIDlet;

/** Minimal RMS: one file per store in the app's private files dir, rewritten atomically on close. */
public class RecordStore {
    private static final Object IO = new Object();
    private final File file;
    private final ArrayList<byte[]> records = new ArrayList<>();

    private RecordStore(File file) throws IOException {
        this.file = file;
        synchronized (IO) {
            if (file.exists()) {
                try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
                    int n = in.readInt();
                    for (int i = 0; i < n; i++) {
                        byte[] b = new byte[in.readInt()];
                        in.readFully(b);
                        records.add(b);
                    }
                } catch (IOException e) {
                    records.clear(); // corrupt store: start empty
                }
            }
        }
    }

    private static File fileFor(String name) {
        return new File(MIDlet.activity().getFilesDir(), "rms_" + name + ".bin");
    }

    public static void deleteRecordStore(String name) throws Exception {
        synchronized (IO) {
            File f = fileFor(name);
            if (f.exists() && !f.delete()) throw new IOException("Unable to delete RecordStore: " + name);
        }
    }

    public static RecordStore openRecordStore(String name, boolean create) throws Exception {
        File f = fileFor(name);
        if (!create && !f.exists()) throw new IOException("RecordStore not found: " + name);
        return new RecordStore(f);
    }

    public int getNumRecords() { return records.size(); }

    public byte[] getRecord(int id) throws Exception {
        if (id < 1 || id > records.size()) throw new IOException("Invalid record id " + id);
        return records.get(id - 1).clone();
    }

    public int addRecord(byte[] b, int off, int len) {
        records.add(Arrays.copyOfRange(b, off, off + len));
        return records.size();
    }

    public void setRecord(int id, byte[] b, int off, int len) throws Exception {
        if (id < 1 || id > records.size()) throw new IOException("Invalid record id " + id);
        records.set(id - 1, Arrays.copyOfRange(b, off, off + len));
    }

    public void closeRecordStore() throws Exception {
        synchronized (IO) {
            File tmp = new File(file.getPath() + ".tmp");
            try (FileOutputStream fo = new FileOutputStream(tmp); DataOutputStream out = new DataOutputStream(fo)) {
                out.writeInt(records.size());
                for (byte[] b : records) {
                    out.writeInt(b.length);
                    out.write(b);
                }
                out.flush();
                fo.getFD().sync();
            }
            if (!tmp.renameTo(file)) {
                file.delete();
                if (!tmp.renameTo(file)) throw new IOException("Unable to save RecordStore");
            }
        }
    }
}
