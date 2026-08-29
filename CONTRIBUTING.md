# Contributing to Resound

Resound is primarily a personal Android project, but focused fixes and useful
improvements are welcome.

## Before opening a pull request

- Keep changes narrowly scoped.
- Preserve the local-first, ad-free design of the app.
- Do not add analytics, accounts, tracking, advertising, or mandatory cloud
  services.
- Match the existing Kotlin / Jetpack Compose style.
- Preserve the premium dark studio visual language unless the change is
  intentionally part of a reviewed redesign.
- Test UI and audio behavior on a physical Android device when possible.
- Do not weaken updater integrity checks.

## Development workflow

1. Create a branch from `main`.
2. Make and test the change.
3. Update `CHANGELOG.md` when the change is user-visible.
4. Build at least the debug APK.
5. Open a pull request describing what changed and how it was tested.

## Bug reports

Include the Resound version, Android version, device model, reproduction steps,
and relevant logs. Do not post private media or security-sensitive details in a
public issue.
