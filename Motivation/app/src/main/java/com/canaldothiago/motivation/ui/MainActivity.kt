package com.canaldothiago.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.canaldothiago.motivation.R
import com.canaldothiago.motivation.databinding.ActivityMainBinding
import com.canaldothiago.motivation.helpers.SecurityPreferences

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        securityPreferences = SecurityPreferences(this)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
        binding.textviewHello.text = "Olá, ${securityPreferences.getString("username",)}!"
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_new_sentence) {
            handleNewSentence()
        }
    }

    private fun handleNewSentence() {

    }

    private fun setListeners() {
        binding.buttonNewSentence.setOnClickListener(this)
    }
}