package com.example.quizz

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContextCompat
import org.json.JSONArray
import java.io.InputStream

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        if (view != null) {
            val startButton = view.findViewById<Button>(R.id.btn_start)
            startButton.setOnClickListener {
                val intent = Intent(requireActivity(), quizzActivity::class.java)
                startActivity(intent)
            }
        }
        // Inflate the layout for this fragment
        return view
    }

}