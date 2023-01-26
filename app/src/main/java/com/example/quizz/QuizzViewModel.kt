package com.example.quizz

import android.content.Context
import android.os.CountDownTimer
import android.system.Os.open
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizz.databinding.ActivityQuizzBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream


class QuizzViewModel : ViewModel() {

    var currentQuestionIndex =  MutableLiveData<Int>()
    private var score =  MutableLiveData<Int>()
    private var questions= MutableLiveData<List<Question>>()
    private var timeLeft = MutableLiveData<Int>()
    private var isTimerRunning = MutableLiveData<Boolean>()
    var answer1 = MutableLiveData<RadioButton>()
    var answer2 = MutableLiveData<RadioButton>()
    var answer3 = MutableLiveData<RadioButton>()
    var answer4 = MutableLiveData<RadioButton>()

    private var countDownTimer: CountDownTimer? = null

    //initiation des variable
    init {
        currentQuestionIndex.value = 0
        score.value = 0
        timeLeft.value = 30000
        isTimerRunning.value = false
        answer1 = MutableLiveData<RadioButton>()
        answer2 = MutableLiveData<RadioButton>()
        answer3 = MutableLiveData<RadioButton>()
        answer4 = MutableLiveData<RadioButton>()
    }
    // fonction pour le timer
    fun startTimer(binding: ActivityQuizzBinding) {
        countDownTimer = object : CountDownTimer(30000.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft.value = millisUntilFinished.toInt()
                binding.timer.text = "Time remaining: ${millisUntilFinished / 1000} seconds"
                isTimerRunning.value = true
            }

            override fun onFinish() {
                binding.nextButton.visibility = View.VISIBLE
                binding.verifyButton.visibility = View.INVISIBLE
                isTimerRunning.value = false
                binding.timer.text = "Vous n'avez pas répondu à temps ! "
            }
        }.start()
    }

    fun stopTimer() {
        countDownTimer?.cancel()
        isTimerRunning.value = false
    }

    data class Question (val question: String, val answers: List<String>, val correctAnswer: String)

    // function pour appeler le fichier JSON
    fun loadJSONFromAsset(context: Context): String {
        val json: String
        try {
            val inputStream: InputStream = context.assets.open("questions.json")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}


