package com.lalee.level5task1.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lalee.level5task1.dao.NoteDao
import com.lalee.level5task1.model.Converters
import com.lalee.level5task1.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "NOTEPAD_DATABASE"

        @Volatile
        private var nodeRoomDatabaseInstance: NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase? {
            if (nodeRoomDatabaseInstance == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    if (nodeRoomDatabaseInstance == null) {
                        nodeRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            NoteRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    nodeRoomDatabaseInstance?.let {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            it.noteDao().insertNote(Note("Title", "", Date()))
                                        }
                                    }
                                }
                            })
                            .build()
                    }
                }
            }
            return nodeRoomDatabaseInstance
        }
    }

}