package com.example.quizz

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val score = intent.getIntExtra("score", 0)
        val totalQuestions = intent.getIntExtra("total_questions", 0)
        val scoreTextView = findViewById<TextView>(R.id.score_text_view)
        val messageTextView = findViewById<TextView>(R.id.message_text_view)
        val backButton = findViewById<Button>(R.id.back_button)

        // calcul du score en poucentage
        val percentage = score.toDouble() / totalQuestions * 100
        scoreTextView.text = "Score: $percentage%"

        // si pourcentage supérieur à 99
        if (percentage > 99) {
            messageTextView.text = "Parfait, tu n'as eu que des bonnes réponses"
        }
        // si pourcentage supérieur à 79
        else if (percentage > 79) {
            messageTextView.text = "Bien joué ! "
        }
        // si pourcentage supérieur à 59
        else if (percentage > 59) {
            messageTextView.text = "Tu peux t'améliorer"
        }
        // si pourcentage supérieur à 39
        else if (percentage > 39) {
            messageTextView.text = "T'es pas ouf !"
        }
        // si pourcentage supérieur à 19
        else if (percentage > 19){
            messageTextView.text = "T'es vraiment pas ouf ! "
        }

        // lorqu'on clique le bouton back on retourne à la page de bienvenue
        backButton.setOnClickListener {
            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)
        }
    }
}