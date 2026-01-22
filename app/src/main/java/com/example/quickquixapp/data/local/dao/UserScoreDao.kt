package com.example.quickquixapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quickquixapp.data.local.entity.UserScoreEntity
import kotlinx.coroutines.flow.Flow

/**
 * ScoreDao: This is the "Data Access Object".
 * It contains the commands (SQL queries) to talk to the Room Database.
 */
@Dao
interface UserScoreDao {

    /**
     * insertScore: Saves a new score. 
     * If the user already exists, it replaces the old score (REPLACE).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: UserScoreEntity)

    /**
     * getAllScores: Gets all scores from the database.
     * It sorts them by 'highScore' so the best players appear at the top.
     * We use Flow so the screen updates automatically when data changes.
     */
    @Query("SELECT * FROM user_scores ORDER BY highScore DESC")
    fun getAllScores(): Flow<List<UserScoreEntity>>

    /**
     * deleteUser: Removes a specific person's score using their name.
     */
    @Query("DELETE FROM user_scores WHERE userName = :name")
    suspend fun deleteUser(name: String)

    /**
     * deleteAllUsers: Wipes the entire leaderboard clean.
     */
    @Query("DELETE FROM user_scores")
    suspend fun deleteAllUsers()
}
