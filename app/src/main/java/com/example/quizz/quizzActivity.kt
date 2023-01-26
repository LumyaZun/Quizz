package com.example.quizz

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.quizz.databinding.ActivityQuizzBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream


class quizzActivity : AppCompatActivity() {

    // instancation des variables à utiliser pour le code
    private val myviewModel : QuizzViewModel by viewModels()
    private var currentQuestionIndex = 0
    private var score = 0
    private lateinit var questions: List<Question>
    private var countDownTimer: CountDownTimer? = null
    private var timeLeft = 30000 // 30 seconds in milliseconds
    private var isTimerRunning = false


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityQuizzBinding = DataBindingUtil.setContentView(this, R.layout.activity_quizz)
        binding.viewModel = myviewModel
        binding.lifecycleOwner = this

        //Lire le fichier JSON de la fonction du viewmodel
        val jsonString = myviewModel.loadJSONFromAsset(this)
        val questionsType = object : TypeToken<List<Question>>() {}.type
        questions = Gson().fromJson<List<Question>>(jsonString, questionsType)

        //rend invisible lebutton suivant
        binding.nextButton.visibility = View.INVISIBLE

        // lance le timer de la fonction du viewmodel
        myviewModel.startTimer(binding)

        // au click du bouton verifier
        binding.verifyButton.setOnClickListener{
            // on rend le bouton verifier invisible
            binding.verifyButton.visibility = View.INVISIBLE

            // on lance la fonction verifyQuestion
            verifyQuestion(binding)

            // on rend le bouton next visible
            binding.nextButton.visibility = View.VISIBLE
        }

        // au click du bouton suivant
        binding.nextButton.setOnClickListener {
            // on rend le bouton verifier visible
            binding.verifyButton.visibility = View.VISIBLE

            // lance le timer de la fonction du viewmodel
            myviewModel.startTimer(binding)

            // si il y a encore des question, on passe à la question suivante
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                updateQuestion(binding)
            }
            // sinon affiche la page de résultat
            else {
                // Show a message that the quiz is over
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total_questions", questions.size)
                startActivity(intent)
            }
        }
        updateQuestion(binding)
    }
    private fun verifyQuestion(binding: ActivityQuizzBinding){
        // lance la fonction stoptimer du viewmodel
        myviewModel.stopTimer()
        val question = questions[currentQuestionIndex]
        binding.questionTextView.text = question.question
        binding.answer1RadioButton.text = question.answers[0]
        binding.answer2RadioButton.text = question.answers[1]
        binding.answer3RadioButton.text = question.answers[2]
        binding.answer4RadioButton.text = question.answers[3]

        // si on click sur le radioButton1
        if (binding.answer1RadioButton.isChecked) {
            // et que la reponse est la bonne
            if (question.answers[0] == question.correctAnswer) {
                // affiche
                Toast.makeText(this, "Réponse correcte, bien joué !", Toast.LENGTH_SHORT).show()
                // augmente le score de 1
                score++
            }
            // et que la reponse n'est pas bonne
            else{
                Toast.makeText(this, "Réponse incorrecte, la bonne réponse était : ${question.correctAnswer}", Toast.LENGTH_SHORT).show()
            }
        }
        // si on click sur le radioButton2
        else if (binding.answer2RadioButton.isChecked) {
            // et que la reponse est la bonne
            if (question.answers[1] == question.correctAnswer) {
                // affiche
                Toast.makeText(this, "Réponse correcte , bien joué !", Toast.LENGTH_SHORT).show()
                // augmente le score de 1
                score++
            }
            // et que la reponse n'est pas bonne
            else{
                Toast.makeText(this, "Réponse incorrecte, la bonne réponse était : ${question.correctAnswer}", Toast.LENGTH_SHORT).show()
            }
        }
        // si on click sur le radioButton3
        else if (binding.answer3RadioButton.isChecked) {
            // et que la reponse est la bonne
            if (question.answers[2] == question.correctAnswer) {
                // affiche
                Toast.makeText(this, "Réponse correcte , bien joué !", Toast.LENGTH_SHORT).show()
                // augmente le score de 1
                score++
            }
            // et que la reponse n'est pas bonne
            else{
                Toast.makeText(this, "Réponse incorrecte, la bonne réponse était : ${question.correctAnswer}", Toast.LENGTH_SHORT).show()
            }
        }
        // si on click sur le radioButton4
        else if (binding.answer4RadioButton.isChecked) {
            // et que la reponse est la bonne
            if (question.answers[3] == question.correctAnswer) {
                // affiche
                Toast.makeText(this, "Réponse correcte , bien joué !", Toast.LENGTH_SHORT).show()
                // augmente le score de 1
                score++
            }
            // et que la reponse n'est pas bonne
            else{
                Toast.makeText(this, "Réponse incorrecte, la bonne réponse était : ${question.correctAnswer}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // fonction pour passer à la question suivante
    private fun updateQuestion(binding: ActivityQuizzBinding) {
        val question = questions[currentQuestionIndex]
        binding.questionTextView.text = question.question
        binding.answer1RadioButton.text = question.answers[0]
        binding.answer2RadioButton.text = question.answers[1]
        binding.answer3RadioButton.text = question.answers[2]
        binding.answer4RadioButton.text = question.answers[3]

        // on rend le bouton next invisible
        binding.nextButton.visibility = View.INVISIBLE

    }


    data class Question (val question: String,
                         val answers: List<String>,
                         val correctAnswer: String)


}


