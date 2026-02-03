package com.example.quickquixapp.data.datasource

import com.example.quickquixapp.domain.model.Question

//this state that questions can come from any source today it is coming from json
//but in future it can be from an api source or a database so it doesnt matters from where the data comes from
//the data must be in the same format as mentioned in this interface or contract
//this is implemented becos sources can be changed and thus we dont want to rewrite our code thats why
//and also we dont have fear of changing the source of data
interface QuizDataSource {
    /**
     * loadQuestions: Should return a list of Question objects.
     */
    fun loadQuestions(): List<Question>
}

//UI
//↓
//ViewModel
//↓
//QuizRepository
//↓
//QuizDataSource (interface)
//↓
//AndroidQuizDataSource (JSON)

// this is abstraction doesnt matters from where data is coming we just need a list of questions
