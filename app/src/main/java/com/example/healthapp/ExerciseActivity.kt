package com.example.healthapp

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.marginTop
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setSupportActionBar(toolbar_exercise_activity)
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupRestView()
    }

    override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        super.onDestroy()
    }

    private fun setRestProgressBar() {
        progressBar.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 10 - restProgress
                tvTimer.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                        this@ExerciseActivity,
                        "here we start the exercise",
                        Toast.LENGTH_SHORT
                ).show()
                getReady.text = " "
                progressBar.max = 30
                setExerciseView()
            }
        }.start()

    }

    private fun setExerciseView() {


        restTimer!!.cancel()
        restProgress = 0

        restTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 30 - restProgress
                tvTimer.text = (30 - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(
                        this@ExerciseActivity,
                        "have a rest",
                        Toast.LENGTH_SHORT
                ).show()
                getReady.text = "GET READY FOR"
                progressBar.max = 10
                setupRestView()
            }
        }.start()
    }

    private fun setupRestView() {
        if (restTimer!=null) {
            restTimer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

}