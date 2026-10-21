# Disco Android port (fixed8)

Native Android port of the Nokia J2ME game `disco.jar` (FARSH, v1.2). The original decompiled game
classes (`com.nokia.mid.appl.disco.*`) run unchanged on top of a small Java ME / Nokia compatibility
layer (`javax.microedition.*`, `com.nokia.mid.ui.*`).

## Build
Open this folder in Code on the Go and run **Build APK**.
If Gradle says "SDK location not found", create `local.properties` in the project root containing
`sdk.dir=<path to your Android SDK>`.

## Controls (touch)
- Title screen: tap anywhere (the menu also opens by itself after 5 seconds)
- While playing, ONLY the three buttons under the game react (no invisible touch zones):
  - Menu: main menu (Continue / New game / High scores / Instructions / Exit, with an "Are you sure?" confirmation)
  - Happy hour: toggles happy hour (the old "key 5"); highlighted while it is on
  - Game options: buy things, hire DJs, take a loan
- Android Back: opens the menu in-game; acts as Back/No inside menus and forms
- Keyboard / d-pad: arrows or WASD, Enter/Space = FIRE, 0-9, Menu key

## Cheat menu
Game options -> last entry "Cheat menu". Tap a row to type a new value (native number dialog):
Money (max 131071), People (max 300), Mood %, Loan owed, Day, Ad fatigue %, Happy hour on/off.
Source: `CheatMenu.java` (values live in class `a`).

## What the compat layer does
- Graphics: MIDP clip semantics (setClip replaces the clip), translate tracking, nearest-neighbour scaling,
  Nokia DirectGraphics flip flags (sprites mirrored correctly), translucent fills, images ignore colour alpha
- Canvas: 176x208 virtual screen letterboxed onto the phone, frame pacing via serviceRepaints,
  key/touch -> Nokia key codes and game actions
- List: full-screen menu in dp units with smooth scrolling (direct drag, fling, rubber-band) and a Back/Exit button bar separated by a clear gap
- Form / Alert: themed Android screens; Alerts auto-dismiss after their timeout (tap to skip)
- RecordStore: atomic saves (high scores, progress)
- Lifecycle: pause/resume via showNotify, clean shutdown, predictive-back (API 33+/36) support

## Audio
`title.mid` plays on the title screen and `title2.mid` during the game (looping, pauses when the app is in the background). The original game had no sound code; this is added by the port (`MusicPlayer.java`).

## Money
`a.START_MONEY` (a.java) is the starting money for the first challenge (now 2000). Buying items now subtracts the price (it used to add it).

## Known gaps
- Verified here with a desktop harness against stand-in Android classes (game flow, rendering,
  pause/exit); not yet run on a physical device.

If it crashes, send the first `FATAL EXCEPTION` block from Logcat.
