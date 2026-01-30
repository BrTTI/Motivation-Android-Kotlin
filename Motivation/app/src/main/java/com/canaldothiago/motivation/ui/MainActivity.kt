package com.canaldothiago.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.canaldothiago.motivation.helpers.MotivationConstants
import com.canaldothiago.motivation.R
import com.canaldothiago.motivation.databinding.ActivityMainBinding
import com.canaldothiago.motivation.repository.SecurityPreferences
import com.canaldothiago.motivation.repository.PhraseRepository

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var securityPreferences: SecurityPreferences
    private var phraseRepository = PhraseRepository()
    private var filter = MotivationConstants.PHRASE.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        securityPreferences = SecurityPreferences(this)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
        getStoredUsername()
        handleFilter(R.id.imageview_all)
        refreshPhrase()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_new_sentence -> refreshPhrase()
            R.id.imageview_all, R.id.imageview_happy, R.id.imageview_sunny -> {
                handleFilter(v.id)
            }
        }
    }

    private fun refreshPhrase() {
        binding.textviewMsgMotivation.text = phraseRepository.getPhrase(filter)
    }

    private fun getStoredUsername() {
        val username = securityPreferences.getString(MotivationConstants.KEY.USERNAME)
        binding.textviewHello.text = username
    }

    fun handleFilter(id: Int) {
        binding.imageviewAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageviewHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageviewSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.imageview_all ->  {
                filter = MotivationConstants.PHRASE.ALL
                binding.imageviewAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            R.id.imageview_happy ->  {
                filter = MotivationConstants.PHRASE.HAPPY
                binding.imageviewHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            R.id.imageview_sunny ->  {
                filter = MotivationConstants.PHRASE.SUNNY
                binding.imageviewSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
        }
    }

    private fun setListeners() {
        binding.buttonNewSentence.setOnClickListener(this)
        println("Botão NOVA FRASE clicado")
        binding.imageviewAll.setOnClickListener(this)
        println("Botão IMAGEVIEW ALL clicado")
        binding.imageviewHappy.setOnClickListener(this)
        println("Botão IMAGEVIEW HAPPY clicado")
        binding.imageviewSunny.setOnClickListener(this)
        println("Botão IMAGEVIEW SUNNY clicado")
    }
}