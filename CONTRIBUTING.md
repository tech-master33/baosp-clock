# Contributing to baosp-clock

Thank you for contributing to baosp-clock, the accessible clock app built for BAOSP.
This guide is written to work well with screen readers and keyboard-only navigation.
Every step is numbered and linear — no visual layout is assumed.

---

## Ways to contribute

1. Report a bug — describe something that does not work as expected
2. Request a feature — describe something that would help blind users
3. Improve documentation — fix unclear steps or add missing information
4. Test builds — install nightly APKs and report accessibility issues
5. Translate — help translate in-app strings to other languages
6. Write code — fix bugs or add features

---

## Claiming an issue

1. Open the issue you want to work on
2. Read the full description, including the "Where to start" section if there is one
3. Leave a comment: "I'd like to take this on"
4. Wait for a maintainer to assign it to you before starting

---

## Before you start

1. A GitHub account — github.com/join
2. Git — git-scm.com
3. Android Studio or VS Code
4. Java 17 — adoptium.net
5. Android SDK (API level 34) — installed by Android Studio

---

## Step 1 — Fork

1. Open github.com/tech-master33/baosp-clock
2. Activate Fork → Create fork
3. Your copy is at github.com/YOUR-USERNAME/baosp-clock

---

## Step 2 — Clone

```bash
git clone https://github.com/YOUR-USERNAME/baosp-clock.git
cd baosp-clock
git remote add upstream https://github.com/tech-master33/baosp-clock.git
```

---

## Step 3 — Branch

```bash
git checkout -b your-branch-name
```

Examples: `fix/alarm-not-firing`, `feature/snooze-button`, `a11y/timer-announcement`

---

## Step 4 — Make changes

Key files:

- `app/src/main/java/org/baosp/clock/` — all Kotlin source
- `app/src/main/res/` — XML layouts and strings
- `app/src/main/AndroidManifest.xml` — permissions and components

### Accessibility rules

1. Every button and label must have a `contentDescription`
2. Touch targets must be at least 48dp
3. All user-facing strings must be in `strings.xml`
4. Alarm and timer completion must announce via TTS, not just vibrate
5. Test with the screen reader on before submitting

---

## Step 5 — Build and test

```bash
chmod +x gradlew
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk
```

Checklist:
- App builds without errors
- Clock time is announced on tap
- Alarms fire and announce at the correct time
- Timer counts down and announces completion
- Stopwatch lap times are announced
- All controls reachable by swipe navigation

---

## Step 6 — Commit

```bash
git add .
git commit -m "fix: alarm does not announce when screen is off

The alarm receiver was calling vibrate but not triggering TTS.
Fixed by acquiring a wakelock and calling the TTS engine directly."
```

Types: `fix`, `feature`, `docs`, `refactor`, `a11y`, `test`

---

## Step 7 — Push and pull request

```bash
git push origin your-branch-name
```

1. Open github.com/YOUR-USERNAME/baosp-clock
2. Activate Compare and pull request
3. Title: one sentence — what changed
4. Description: what problem does this solve, how did you test it
5. Activate Create pull request

---

## Reporting a bug

1. Open github.com/tech-master33/baosp-clock/issues
2. Activate New issue → Bug report
3. Include: what you were doing, what happened, your Android version and device

---

## Community

- Issues: github.com/tech-master33/baosp-clock/issues
- Discussions: github.com/tech-master33/baosp-clock/discussions
- Screen reader: github.com/tech-master33/baosp-screenreader
- TTS engine: github.com/tech-master33/baosp-tts
- BAOSP main: github.com/tech-master33/baosp
