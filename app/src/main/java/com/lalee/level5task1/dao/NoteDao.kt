package com.lalee.level5task1.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.lalee.level5task1.model.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM Note LIMIT 1")
    fun getNotePad(): LiveData<Note?>

    @Update
    suspend fun updateNote(note: Note)
}