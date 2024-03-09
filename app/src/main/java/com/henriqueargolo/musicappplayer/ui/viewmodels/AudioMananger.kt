package com.henriqueargolo.musicappplayer.ui.viewmodels

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.henriqueargolo.musicappplayer.data.model.AudioFile

class AudioMananger(val context: Context) {
    fun getAllAudioFiles(): List<AudioFile> {
        val audioFIle = mutableListOf<AudioFile>()

        val audioInfo = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA
        )
        val selection = null
        val selectionArgs = null

        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

        context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            audioInfo,
            selection,
            selectionArgs,
            sortOrder

        )?.use { cursor ->
            if (cursor.moveToFirst()) {
                do {
                    val id =
                        cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                    val title =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                    val artist =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))
                    val duration =
                        cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                    val path =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))

                    val audio = AudioFile(id, title, artist, duration, path)
                    audioFIle.add(audio)
                } while (cursor.moveToNext())

            }
        }

        return audioFIle
    }
}
