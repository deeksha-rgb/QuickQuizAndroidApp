package com.example.quickquixapp.data.local.entity

//Entity defines the schema of the database table and maps Kotlin objects directly to SQLite rows.
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_scores")
data class UserScoreEntity(

    @PrimaryKey
    val userName: String,

    val highScore: Int
)
