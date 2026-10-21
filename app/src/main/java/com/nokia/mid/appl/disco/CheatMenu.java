package com.nokia.mid.appl.disco;

import com.example.disco.NumberPrompt;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

/**
 * Cheat menu: edit the values of the running game (money, people, mood, loan, day, ...).
 * The game is paused while menus are open, so edits are applied safely.
 */
class CheatMenu implements CommandListener {
    private static final int MONEY = 0, PEOPLE = 1, MOOD = 2, LOAN = 3, DAY = 4, ADS = 5, HAPPY = 6, COUNT = 7;
    private static final String[] NAMES = {"Money ($)", "People", "Mood (%)", "Loan owed ($)", "Day", "Ad fatigue (%)", "Happy hour"};

    private final a st;
    private final Display display;
    private final Displayable back;
    private final List list;
    private final Command backCommand;

    CheatMenu(a state, Display display, Displayable back) {
        this.st = state;
        this.display = display;
        this.back = back;
        this.list = new List("Cheat menu", List.IMPLICIT);
        this.backCommand = new Command("Back", Command.BACK, 1);
        this.list.addCommand(this.backCommand);
        this.list.setCommandListener(this);
        for (int i = 0; i < COUNT; i++) {
            this.list.append(label(i), (Image) null);
        }
    }

    void show() {
        refresh();
        this.display.setCurrent(this.list);
    }

    // ---- reading the game state -------------------------------------------------
    private long read(int field) {
        switch (field) {
            case MONEY: return this.st.s();
            case PEOPLE: return this.st.b();
            case MOOD: return clamp((100L * this.st.d()) / 150L, 0, 100);
            case LOAN: return this.st.q();
            case DAY: return (this.st.k() / 30) + 1;
            case ADS: return (100L * this.st.v) / 75000L;
            default: return this.st.e() ? 1 : 0;
        }
    }

    private String label(int field) {
        if (field == HAPPY) {
            return NAMES[field] + ": " + (this.st.e() ? "ON" : "off");
        }
        return NAMES[field] + ": " + read(field);
    }

    private static long clamp(long v, long lo, long hi) {
        return v < lo ? lo : (v > hi ? hi : v);
    }

    private void refresh() {
        for (int i = 0; i < COUNT; i++) {
            this.list.set(i, label(i), (Image) null);
        }
    }

    // ---- writing the game state -------------------------------------------------
    private void write(int field, long value) {
        a g = this.st;
        switch (field) {
            case MONEY:
                g.z = (int) clamp(value, 0, 131071) << 14;
                break;
            case PEOPLE:
                g.s = (int) clamp(value, 0, 300) << 7;
                g.o = g.s;
                break;
            case MOOD: {
                int target = ((int) clamp(value, 0, 100) * 150 / 100) << 14;
                int delta = target - g.y;
                g.y = target;
                g.p += delta; // keep the game's own mood target moving with it so the change sticks
                break;
            }
            case LOAN:
                g.k = (int) clamp(value, 0, 131071) << 14;
                break;
            case DAY: {
                long newH = Math.max(1L, (clamp(value, 1, 9999) - 1) * 30L * 1000L);
                long oldH = Math.max(1L, g.h);
                g.x = (g.x * newH) / oldH; // keep the average mood / people unchanged
                g.g = (g.g * newH) / oldH;
                g.h = newH;
                break;
            }
            case ADS:
                g.v = (int) (clamp(value, 0, 100) * 75000L / 100L);
                break;
            default:
                g.a(value != 0);
                break;
        }
    }

    @Override
    public void commandAction(Command command, Displayable displayable) {
        if (command == this.backCommand) {
            this.display.setCurrent(this.back);
            return;
        }
        final int field = this.list.getSelectedIndex();
        if (field < 0 || field >= COUNT) {
            return;
        }
        if (field == HAPPY) {
            write(HAPPY, this.st.e() ? 0 : 1);
            refresh();
            return;
        }
        NumberPrompt.show(NAMES[field], read(field), new NumberPrompt.Callback() {
            @Override
            public void onValue(long value) {
                write(field, value);
                refresh();
            }
        });
    }
}
