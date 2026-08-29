# Security Policy

## Supported versions

Security fixes are made against the current development line and the newest
published stable release when one exists.

## Reporting a vulnerability

Please **do not open a public issue** for a vulnerability that could expose user
data, bypass updater verification, allow arbitrary file access, or otherwise
create a security risk.

Use GitHub's private **Security Advisories** feature for this repository when it
is available. Include:

- affected Resound version or commit;
- Android version and device model;
- clear reproduction steps;
- expected and observed behavior;
- logs or proof-of-concept details that are safe to share privately.

## Updater security model

Resound's updater is intentionally user-initiated and validates:

- HTTPS-only update URLs;
- trusted GitHub download hosts;
- manifest SHA-256;
- APK package name;
- APK signing certificate against the installed application.

A change of signing key is therefore treated as an invalid update rather than a
normal upgrade path.
