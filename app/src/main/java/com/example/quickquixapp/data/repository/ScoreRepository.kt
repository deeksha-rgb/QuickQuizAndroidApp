package com.example.quickquixapp.data.repository

import com.example.quickquixapp.data.local.dao.UserScoreDao
import com.example.quickquixapp.data.local.entity.UserScoreEntity
import kotlinx.coroutines.flow.Flow

/**
 * ScoreRepository: Manages the high score data between the Database and the ViewModels.
 */
class ScoreRepository(
    private val userScoreDao: UserScoreDao
) {

    /**
     * saveScore: Saves a new score to the database.
     * Uses 'suspend' because database operations take time.
     */
    suspend fun saveScore(userName: String, score: Int) {
        val userScore = UserScoreEntity(
            userName = userName,
            highScore = score
        )
        userScoreDao.insertScore(userScore)
    }

    /**
     * getAllScores: Returns a Flow (live stream) of all scores.
     */
   fun getAllScores(): Flow<List<UserScoreEntity>> {
        return userScoreDao.getAllScores()
    }

    /**
     * deleteUser: Removes a specific score.
     */
    suspend fun deleteUser(userName: String) {
        userScoreDao.deleteUser(userName)
    }

    /**
     * deleteAllUsers: Clears the whole leaderboard.
     */
    suspend fun deleteAllUsers() {
        userScoreDao.deleteAllUsers()
    }
}
