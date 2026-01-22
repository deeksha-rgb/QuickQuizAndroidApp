package com.example.quickquixapp.di

import android.content.Context
import com.example.quickquixapp.data.datasource.AndroidQuizDataSource
import com.example.quickquixapp.data.QuizPreferenceManager
import com.example.quickquixapp.data.repository.QuizRepository
import com.example.quickquixapp.data.local.database.QuizDatabase
import com.example.quickquixapp.data.repository.ScoreRepository

///**
// * AppContainer: This class is like a "Toolbox" for your app.
// * It creates all the important parts (like Databases and Managers) in one place
// * so they can be easily shared across the entire app.
// */
class
AppContainer(context: Context) {

    // --- DATABASE SETUP ---
    //  Get or create the Room Database
    private val database = QuizDatabase.getDatabase(context)
    
    //  Get the DAO (the object that runs SQL commands)
    private val scoreDao = database.scoreDao()
    
    //  Create the Score Manager (Repository) for high scores
    val scoreRepository = ScoreRepository(scoreDao)

    // --- QUIZ DATA SETUP ---
    //  Create the object that loads questions from the JSON file
    private val quizDataSource = AndroidQuizDataSource(context)
    
    //  Create the object that saves simple settings (like Light/Dark mode)
    private val quizPreferenceManager = QuizPreferenceManager(context)

    // --- REPOSITORY SETUP ---
    //  Create the main Quiz Manager that the ViewModels will talk to
    val quizRepository = QuizRepository(
        dataSource = quizDataSource,
        preferenceManager = quizPreferenceManager
    )
}
