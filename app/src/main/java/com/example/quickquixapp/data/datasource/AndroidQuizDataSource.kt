package com.example.quickquixapp.data.datasource

import android.content.Context
import com.example.quickquixapp.domain.model.Question
import com.google.gson.Gson

/**
 * AndroidQuizDataSource: This class is responsible for loading the quiz questions
 * from a JSON file stored in the app's 'assets' folder.
 */
class AndroidQuizDataSource(
    private val context: Context
) : QuizDataSource {

    /**
     * loadQuestions: Reads 'questions.json' and converts it into a list of Question objects.
     */
    override fun loadQuestions(): List<Question> {
        // 1. Open the JSON file from the assets folder
        val inputStream = context.assets.open("questions.json")

        // 2. Read all the text from the file
        val json = inputStream.bufferedReader().use { it.readText() }

        // 3. Use Gson library to convert the JSON text into a 'QuestionResponse' object
        val response = Gson().fromJson(
            json,
            QuestionResponse::class.java
        )

        // 4. Return the list of questions found inside the response
        return response.questions
    }
}