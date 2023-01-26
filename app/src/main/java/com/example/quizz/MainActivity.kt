package com.example.quizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.PhoneAccount.builder
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()

        //creation bottom Nvaigation view
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

    }
    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.home ->{
                currentFragment = HomeFragment()
            }
            R.id.rules ->{
                currentFragment = RulesFragment()
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,currentFragment).commit()
        true
    }
}