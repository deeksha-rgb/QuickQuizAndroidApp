package com.example.quickquixapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quickquixapp.data.local.dao.UserScoreDao
import com.example.quickquixapp.data.local.entity.UserScoreEntity

/**
 * QuizDatabase: The main database class for the app.
 * It stores user scores permanently on the phone using the Room library.
 */
@Database(
    entities = [UserScoreEntity::class], // This tells Room which tables to create
    version = 1,
    exportSchema = false
)
abstract class QuizDatabase : RoomDatabase() {

    // This function gives us access to the 'ScoreDao' to run SQL commands
    abstract fun scoreDao(): UserScoreDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        /**
         * getDatabase: Creates the database if it doesn't exist, 
         * or returns the existing one. This ensures we only have ONE database open.
         */

//We use a "Singleton.since database creation is a heavy process so we create it only once
//
// " This variable INSTANCE checks if the database is already open.
//◦
//If it's open, it uses the existing one.
//◦
//If it's closed, it opens it for the first time.
        fun getDatabase(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_database" // The name of the database file on the phone
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}
