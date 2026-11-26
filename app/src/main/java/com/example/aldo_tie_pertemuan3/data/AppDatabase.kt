package com.example.aldo_tie_pertemuan3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aldo_tie_pertemuan3.data.dao.NoteDao
import com.example.aldo_tie_pertemuan3.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class], // tambahkan entitas baru di sini
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    /* Tambahkan Dao baru di sini */

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}