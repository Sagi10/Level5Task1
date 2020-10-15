package com.lalee.level5task1.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.lalee.level5task1.dao.NoteDao
import com.lalee.level5task1.database.NoteRoomDatabase
import com.lalee.level5task1.model.Note

class NoteRepository(context: Context) {

    private val noteDao: NoteDao

    init {
        val database = NoteRoomDatabase.getDatabase(context)
        noteDao = database!!.noteDao()
    }

    fun getNotepad(): LiveData<Note?> {
        return noteDao.getNotePad()
    }

    suspend fun updateNotepad(note: Note){
        noteDao.updateNote(note)
    }
}