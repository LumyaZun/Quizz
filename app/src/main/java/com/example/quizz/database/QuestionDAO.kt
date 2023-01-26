package com.example.quizz.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizz.quizzActivity

@Dao
interface QuestionDao {
    @Insert
    fun insert(question: quizzActivity.Question)

    @Query("SELECT * FROM question")
    fun getAllQuestion(): List<Question>
}