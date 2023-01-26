package com.example.quizz.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val question: String,
    @ColumnInfo val answers: List<String>,
    @ColumnInfo val correctAnswer: String
)