# Releasing Resound

Resound is sideloaded and updates through a signed APK plus a small JSON
manifest.

## Release checklist

1. Complete physical-device regression testing.
2. Set the final `versionName` and monotonically increasing `versionCode`.
3. Update `CHANGELOG.md`.
4. Build the release APK with the established Resound signing key.
5. Verify the APK installs over the previous release.
6. Compute SHA-256 for the exact APK that will be published.
7. Update `releases/update.json` with the final version, URL, hash, and notes.
8. Verify the manifest URL and APK URL are HTTPS GitHub URLs accepted by the app.
9. Commit the manifest and release metadata.
10. Tag the release only after the final APK and manifest are verified.
11. Create the GitHub Release and attach the signed APK.
12. Re-check the in-app updater from the previous release.

## Update manifest

The production manifest is:

`releases/update.json`

Required fields:

```json
{
  "versionCode": 20,
  "versionName": "0.8.0",
  "apkUrl": "https://github.com/MikereDD/Resound/releases/download/v0.8.0/Resound-v0.8.0.apk",
  "sha256": "64-lowercase-hex-characters",
  "notes": "Release notes"
}
```

Do not publish a manifest before the referenced APK exists at the exact URL.
The updater rejects a wrong hash, wrong package name, or mismatched signing
certificate by design.
