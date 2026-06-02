# baosp-clock Roadmap

What we plan to build next, why it matters, and what state each item is in.

Open an issue or discussion before starting work on anything here so we can coordinate.

---

## Status key

- **Planned** — not started
- **In progress** — actively being worked on
- **Needs help** — no one assigned, good place to contribute
- **Done** — shipped in the nightly build

---

## Alarm

### Repeating alarms — Planned

**What it is:** Set alarms that repeat on chosen days of the week (e.g. weekdays only).

**Why it matters:** Right now alarms are one-shot. Most users need a weekday wake-up alarm they do not have to reset every evening.

**Proposed approach:** Add a day-picker in the alarm creation screen. Store the repeat mask in a Room database row per alarm. Use `AlarmManager.setRepeating()` or reschedule on each fire.

---

### Multiple alarms — Planned

**What it is:** Store and manage more than one alarm at a time.

**Why it matters:** One alarm is enough for a wake-up, but not for medication reminders, appointments, or other daily events.

**Proposed approach:** A list screen — each alarm is a row with time, label, and on/off toggle. Navigable by swipe; each row announces its time and label.

---

### Alarm label — Needs help

**What it is:** Give each alarm a text label so the screen reader can announce what the alarm is for.

**Why it matters:** "Alarm" is not useful. "Medication 9am" or "Meeting 2pm" tells the user immediately why the phone is ringing.

**Where to start:** Add a text field in the alarm creation screen. Store the label alongside the alarm time. Read the label aloud when the alarm fires.

---

### Snooze — Needs help

**What it is:** Dismiss an alarm for a fixed short period (e.g. 10 minutes) with one gesture.

**Why it matters:** Many users need to snooze an alarm without navigating a complex screen at the moment of waking.

**Proposed approach:** When an alarm fires, show a full-screen panel with two large buttons: Snooze and Dismiss. Both must be reachable with the first swipe and announced clearly.

---

## Timer

### Multiple timers — Planned

**What it is:** Run more than one countdown timer simultaneously.

**Why it matters:** Cooking often requires timing several things at once. A single timer forces users to do mental arithmetic.

---

### Timer label — Needs help

**What it is:** Give each timer a text label ("Pasta", "Sauce").

**Why it matters:** When two timers fire close together, the screen reader must announce which one finished.

---

## Clock

### World clock — Planned

**What it is:** Show the current time in additional cities or time zones.

**Why it matters:** Users who communicate with people in other countries need to know local time there without doing time-zone arithmetic.

---

### Talking clock shortcut — Needs help

**What it is:** A widget or quick-panel button that announces the current time aloud with one tap, without opening the app.

**Why it matters:** Checking the time is the most common use of a clock. Having to open the app every time adds unnecessary steps.

**Where to start:** An app widget or a tile in baosp-panel.

---

## How priorities are set

Items move up the list when more users report being blocked or a contributor volunteers to lead the work.
Every item here has a stated impact on blind or disabled users.
