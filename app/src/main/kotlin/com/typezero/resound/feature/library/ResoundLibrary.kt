package com.typezero.resound.feature.library

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.typezero.resound.core.io.Outputs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** One audio export published by Resound into Music/Resound. */
data class LibraryAudio(
    val uri: Uri,
    val displayName: String,
    val mimeType: String,
    val sizeBytes: Long,
    val durationMs: Long,
    val modifiedSeconds: Long,
)

object ResoundLibrary {
    suspend fun load(context: Context): List<LibraryAudio> = withContext(Dispatchers.IO) {
        val resolver = context.contentResolver
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            @Suppress("DEPRECATION")
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        }

        val projection = buildList {
            add(MediaStore.Audio.Media._ID)
            add(MediaStore.Audio.Media.DISPLAY_NAME)
            add(MediaStore.Audio.Media.MIME_TYPE)
            add(MediaStore.Audio.Media.SIZE)
            add(MediaStore.Audio.Media.DURATION)
            add(MediaStore.Audio.Media.DATE_MODIFIED)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                add(MediaStore.Audio.Media.RELATIVE_PATH)
            } else {
                @Suppress("DEPRECATION")
                add(MediaStore.Audio.Media.DATA)
            }
        }.toTypedArray()

        val (selection, args) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            "${MediaStore.Audio.Media.RELATIVE_PATH} LIKE ?" to
                arrayOf("${Environment.DIRECTORY_MUSIC}/${Outputs.SUBDIR}/%")
        } else {
            @Suppress("DEPRECATION")
            "${MediaStore.Audio.Media.DATA} LIKE ?" to
                arrayOf("%/${Environment.DIRECTORY_MUSIC}/${Outputs.SUBDIR}/%")
        }

        val items = mutableListOf<LibraryAudio>()
        resolver.query(
            collection,
            projection,
            selection,
            args,
            "${MediaStore.Audio.Media.DATE_MODIFIED} DESC",
        )?.use { cursor ->
            val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val nameIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
            val mimeIndex = cursor.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE)
            val sizeIndex = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE)
            val durationIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)
            val modifiedIndex = cursor.getColumnIndex(MediaStore.Audio.Media.DATE_MODIFIED)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idIndex)
                items += LibraryAudio(
                    uri = ContentUris.withAppendedId(collection, id),
                    displayName = cursor.getString(nameIndex) ?: "audio",
                    mimeType = mimeIndex.takeIf { it >= 0 }?.let(cursor::getString) ?: "audio/*",
                    sizeBytes = sizeIndex.takeIf { it >= 0 }?.let(cursor::getLong) ?: 0L,
                    durationMs = durationIndex.takeIf { it >= 0 }?.let(cursor::getLong) ?: 0L,
                    modifiedSeconds = modifiedIndex.takeIf { it >= 0 }?.let(cursor::getLong) ?: 0L,
                )
            }
        }
        items
    }
}
