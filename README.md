# baosp-clock

[![Android CI — baosp-clock](https://github.com/tech-master33/baosp-clock/actions/workflows/android.yml/badge.svg)](https://github.com/tech-master33/baosp-clock/actions/workflows/android.yml)

An accessible clock app with alarm, countdown timer, and stopwatch — designed for blind and visually impaired users. Part of the [BAOSP](https://github.com/tech-master33/baosp) ecosystem.

## Download

**Latest APK → [github.com/tech-master33/baosp/releases/tag/nightly](https://github.com/tech-master33/baosp/releases/tag/nightly)**

A fresh build is posted there automatically every night alongside the screen reader, TTS engine, launcher, calculator, and panel.
You can also find standalone builds on the [releases page](https://github.com/tech-master33/baosp-clock/releases) of this repo.

## Features

- **Accessible clock face** — current time announced on tap, large readable display
- **Alarm** — set alarms with spoken confirmation; alarm fires with vibration and TTS announcement
- **Countdown timer** — tap to start/stop, remaining time announced on focus
- **Stopwatch** — start, stop, lap — all actions spoken aloud
- **Large touch targets** — every control at least 48dp, designed for touch-without-looking
- **Works with baosp-screenreader** — all elements fully labelled for the BAOSP screen reader

## Installing on your device

1. Download the APK from the nightly link above
2. Transfer it to your Android device
3. Install it (allow unknown sources if prompted)
4. Open **BAOSP Clock** from your app drawer

## Building locally

```bash
git clone https://github.com/tech-master33/baosp-clock.git
cd baosp-clock
chmod +x gradlew
./gradlew assembleDebug
# APK at: app/build/outputs/apk/debug/app-debug.apk
```

Requires JDK 17 and Android SDK with API level 34.

## Permissions

| Permission | Why it is needed |
|-----------|-----------------|
| `VIBRATE` | Alarm vibration |
| `POST_NOTIFICATIONS` | Alarm and timer notifications |
| `SCHEDULE_EXACT_ALARM` | Alarms fire at the exact time set |

## CI/CD

Every push to `master` automatically builds a new APK and posts it as a GitHub Release.
The badge above shows whether the latest build passed or failed.

## BAOSP Ecosystem

baosp-clock is part of BAOSP — an accessible Android platform for blind and visually impaired users:

| Repo | What it does |
|------|-------------|
| [baosp](https://github.com/tech-master33/baosp) | Main project — nightly bundle, coordination |
| [baosp-screenreader](https://github.com/tech-master33/baosp-screenreader) | Screen reader — accessibility service |
| [baosp-tts](https://github.com/tech-master33/baosp-tts) | SVOX Pico TTS engine |
| [aoler](https://github.com/tech-master33/aoler) | Accessible home screen launcher |
| **[baosp-clock](https://github.com/tech-master33/baosp-clock)** | **Accessible clock, alarm, timer (this repo)** |
| [baosp-calc](https://github.com/tech-master33/baosp-calc) | Accessible calculator |
| [baosp-panel](https://github.com/tech-master33/baosp-panel) | Quick-access control panel |

All APKs are bundled together and published every night at  
**[github.com/tech-master33/baosp/releases/tag/nightly](https://github.com/tech-master33/baosp/releases/tag/nightly)**

## License

Apache License 2.0 — same as BAOSP and AOSP.
