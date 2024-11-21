package com.github.shortmikelon.rgr3

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

fun getAllAudioFiles(context: Context): List<AudioFile> {
    val audioList = mutableListOf<AudioFile>()
    val projection = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.DISPLAY_NAME,
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) MediaStore.Audio.Media.DATA else null, // Путь к файлу
        MediaStore.Audio.Media.DURATION, // Длительность
        MediaStore.Audio.Media.SIZE // Размер файла
    ).filterNotNull().toTypedArray() // Убираем null для Android 10+

    val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0" // Только музыкальные файлы
    val sortOrder = "${MediaStore.Audio.Media.DATE_ADDED} DESC"

    val query = context.contentResolver.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection,
        null,
        null,
        sortOrder
    )

    query?.use { cursor ->
        val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
        val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)
        val pathColumn = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
        } else {
            -1 // Для Android 10+ путь не используется
        }
        val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
        val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)

        while (cursor.moveToNext()) {
            val id = cursor.getLong(idColumn)
            val name = cursor.getString(nameColumn)
            val path = if (pathColumn != -1) cursor.getString(pathColumn) else null
            val duration = cursor.getLong(durationColumn)
            val size = cursor.getLong(sizeColumn)

            // URI используется для Android 10+
            val uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)

            audioList.add(AudioFile(id, name, path, uri, duration, size))
        }
    }

    return audioList
}

data class AudioFile(
    val id: Long,
    val name: String,
    val path: String?, // Может быть null для Android 10+
    val uri: Uri,
    val duration: Long,
    val size: Long
)
