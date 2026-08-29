# Third-party notices

Resound includes or depends on third-party software that is **not** relicensed
under Resound's project license.

## FFmpeg / FFmpegKit

Resound uses an FFmpegKit-compatible Android wrapper through:

`com.moizhassan.ffmpeg:ffmpeg-kit-16kb:6.1.1`

FFmpeg and the wrapper are distributed under their own applicable open-source
licenses. The exact obligations depend on the codecs and libraries present in
the binary used for a particular build. Resound's Apache-2.0 license does not replace
or override those terms.

Before distributing a release APK, verify the license configuration of the
resolved FFmpeg package and include any notices or source-offer requirements
that apply to that build.

## Android / Jetpack / Kotlin

AndroidX, Jetpack Compose, Kotlin, kotlinx.coroutines, and their transitive
dependencies remain subject to their respective upstream licenses.

This file is informational and is not a substitute for the license text shipped
by each dependency.
