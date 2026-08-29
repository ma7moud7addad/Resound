# Resound code audit — 2026-07-27

## Fixed in this source

- Added a user-initiated updater rather than silent/background installation.
- Restricted update metadata and APK downloads to HTTPS and an explicit GitHub host allowlist.
- Added SHA-256 verification before install.
- Added package-name verification so a different application cannot be handed to the installer.
- Added signing-certificate verification against the currently installed Resound certificate.
- Added FileProvider URI sharing; the updater never exposes a raw `file://` URI.
- Added Android's per-app unknown-source permission flow for sideload installation.
- Added release manifest files and documented the release workflow.

## Important deferred work

- Editor and timeline state still live in composable-local `remember` state. Rotation is handled through `configChanges`, but process death can still discard the project. Move durable screen state into ViewModels plus `SavedStateHandle`.
- Long FFmpeg operations have no visible Cancel action even though `FFmpegKitRunner` supports coroutine cancellation. Keep a Job per operation and expose Cancel in both editor and timeline.
- Source/cache files created for FFmpeg are not consistently deleted after every success/failure path. Add a scoped temp-file manager and `finally` cleanup.
- `Outputs.publishToMusic` should delete a partially inserted MediaStore row if stream copy or finalization fails.
- Recorder lifecycle should stop/release safely when the screen or process is interrupted mid-recording.
- The app requests legacy storage permission at startup on API 26–28 instead of only when export is attempted.
- No automated unit/instrumentation tests are present for effect argument generation, updater manifest parsing, hash validation, or timeline math.
- The Gradle wrapper is not included, preventing a fully reproducible command-line build from a clean checkout.

## Release caution

The updater intentionally requires the downloaded APK to use the same signing certificate as the installed app. A debug-signed build cannot update a release-signed build, and changing the release key will be rejected by design.

## Standalone GitHub readiness pass

- Active repository/changelog links now target `MikereDD/Resound` instead of the old monorepo path.
- The updater manifest endpoint now targets the standalone repository.
- Added production/example update manifests, release documentation, project license, third-party notices, security policy, contribution guide, GitHub issue templates, and a debug-build CI workflow.
- Preserved the approved premium UI mockup under `docs/design/` as the public visual reference.
