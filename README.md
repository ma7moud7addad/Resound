<p align="center">
  <img src="icon.png" width="120" alt="Resound" />
</p>

<h1 align="center">Resound</h1>

<p align="center">
  A local-first audio editor and multitrack mixer for Android.<br />
  No account. No ads. Processing stays on-device.
</p>

<p align="center">
  <img alt="Android" src="https://img.shields.io/badge/Android-26%2B-3DDC84?logo=android&logoColor=white" />
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.0.21-7F52FF?logo=kotlin&logoColor=white" />
  <img alt="Jetpack Compose" src="https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4" />
  <img alt="License" src="https://img.shields.io/badge/license-WTFPL-lightgrey" />
</p>

Resound opens or records audio, renders a decoded waveform, applies edits through
FFmpeg, arranges clips across a multitrack timeline, and exports results to the
shared `Music/Resound` library.

The app is currently in the **0.8 development line** while the premium interface
and release infrastructure are being finalized.

## Highlights

- **Waveform Editor** — real `MediaCodec`-decoded waveform, draggable selection,
  playhead, playback, and detailed timecode.
- **Audio tools** — Trim, Mix, Concat, Fade, Volume, Speed, Pitch, EQ,
  Vocal Remove, Convert, and Compress.
- **Recorder** — record AAC/M4A audio and load it directly into the editor.
- **Multitrack** — multiple tracks, clip positioning, clip-edge trimming,
  synchronized zoom/scroll, mute controls, and mixdown export.
- **Library** — browse exported files in `Music/Resound`, refresh, share, or
  reopen them directly in the Editor.
- **Ringtones** — set loaded audio as the system ringtone.
- **Secure updater** — user-initiated update checks with HTTPS-only endpoints,
  SHA-256 verification, package-name validation, and signing-certificate matching.
- **Local-first** — no account, no advertising, and no cloud requirement.

## Premium interface

The v0.8 line rebuilds Resound around a premium dark studio interface using its
signal-teal waveform identity. The approved design reference is stored at:

`docs/design/Resound-premium-mockup.png`

<p align="center">
  <img src="docs/design/Resound-premium-mockup.png" width="900" alt="Resound premium interface mockup" />
</p>

## Current status

**Source version:** `0.8.0-dev.6` (`versionCode 19`)

Core editing, recording, playback, ringtone, multitrack, Library, and secure
update-check workflows are implemented. The current focus is real-device
regression testing and preparing the eventual `0.8.0` release.

## Download

The last legacy APK retained in the repository is `v0.6.2`:

**[Download Resound v0.6.2](https://github.com/MikereDD/Resound/raw/main/releases/Resound-v0.6.2.apk)**

The next public release will be published through **GitHub Releases** after the
0.8 release-readiness pass is complete.

Resound currently targets **arm64-v8a** devices and is designed for sideloading.

## Build

Requirements:

- JDK 17
- Android SDK 35
- Gradle 8.9
- AGP 8.7.2
- Kotlin 2.0.21

Build with a locally installed Gradle:

```bash
gradle :app:assembleDebug
```

or generate a local wrapper if desired:

```bash
gradle wrapper --gradle-version 8.9
./gradlew :app:assembleDebug
```

The Gradle wrapper is intentionally not committed. GitHub Actions installs
Gradle 8.9 explicitly for CI builds.

## FFmpeg dependency

Resound uses `FFmpegRunner`, implemented by `FFmpegKitRunner`, with the
16 KB-page-size compatible community republish:

```kotlin
implementation("com.moizhassan.ffmpeg:ffmpeg-kit-16kb:6.1.1")
```

The FFmpeg engine has its own licensing requirements. See
[`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md).

## Updating Resound

The in-app updater reads:

`https://raw.githubusercontent.com/MikereDD/Resound/main/releases/update.json`

A release APK is accepted only when all updater checks succeed, including the
expected SHA-256, package name, and signing certificate.

See [`releases/update.example.json`](releases/update.example.json) for the
manifest format and [`docs/RELEASING.md`](docs/RELEASING.md) for the release
workflow.

## Project history

Resound originally lived inside the `It-Works-On-My-Machine` monorepository.
Its application-specific Git history was extracted into this standalone
repository with `git filter-repo`.

See [`docs/MONOREPO-MIGRATION.md`](docs/MONOREPO-MIGRATION.md).

## Contributing and security

This is primarily a personal project, but focused bug reports and pull requests
are welcome. See [`CONTRIBUTING.md`](CONTRIBUTING.md).

For security-sensitive reports, see [`SECURITY.md`](SECURITY.md).

## License

Resound's original source is released under the **WTFPL**, subject to the
separate licenses of third-party dependencies. See [`LICENSE`](LICENSE) and
[`THIRD_PARTY_NOTICES.md`](THIRD_PARTY_NOTICES.md).

## Changelog

See [`CHANGELOG.md`](CHANGELOG.md).

---

<p align="center">Built by Typezer∅</p>
