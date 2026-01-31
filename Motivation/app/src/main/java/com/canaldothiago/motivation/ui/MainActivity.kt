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

        // Esconde a barra de ação
        supportActionBar?.hide()

        // Inicialização de dados
        securityPreferences = SecurityPreferences(this)

        // Configuração de Insets (Edge-to-Edge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicialização de métodos
        setListeners()
        getStoredStrings()
        handleFilter(R.id.imageview_all)
        refreshPhrase()
    }


    /** Método para tratamento de eventos de clique */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.button_new_sentence -> refreshPhrase()
            R.id.imageview_all, R.id.imageview_happy, R.id.imageview_sunny -> {
                handleFilter(v.id)
            }
        }
    }


    /** Atualiza o texto da frase de motivação */
    private fun refreshPhrase() {
        binding.textviewMsgMotivation.text = phraseRepository.getPhrase(filter)
        setStoredPhrase()
    }

    /* Pega os dados salvos no shared preferences e exibe na tela */
    private fun getStoredStrings() {
        binding.textviewHello.text = securityPreferences.getString(MotivationConstants.KEY.USERNAME)
        binding.textviewMsgMotivation.text = securityPreferences.getString(MotivationConstants.KEY.PHRASE)
    }

    /** Pega o nome do usuário armazenado na security preferences e exibe na tela */
    private fun getStoredUsername() {
        val username = securityPreferences.getString(MotivationConstants.KEY.USERNAME)
        binding.textviewHello.text = username
    }

    private fun setStoredPhrase() {
        securityPreferences.storeString(MotivationConstants.KEY.PHRASE, binding.textviewMsgMotivation.text.toString())
    }

    private fun getStoredPhrase() {
        val phrase = securityPreferences.getString(MotivationConstants.KEY.PHRASE)
        binding.textviewMsgMotivation.text = phrase
    }

    /** Filtra as frases e atualiza a cor do ícone selecionado */
    fun handleFilter(id: Int) {
        binding.imageviewAll.isSelected = false
        binding.imageviewHappy.isSelected = false
        binding.imageviewSunny.isSelected = false

        when (id) {
            R.id.imageview_all -> {
                filter = MotivationConstants.PHRASE.ALL
                binding.imageviewAll.isSelected = true
            }

            R.id.imageview_happy -> {
                filter = MotivationConstants.PHRASE.HAPPY
                binding.imageviewHappy.isSelected = true
            }

            R.id.imageview_sunny -> {
                filter = MotivationConstants.PHRASE.SUNNY
                binding.imageviewSunny.isSelected = true
            }
        }
    }

    /** Configura os listeners (eventos de clique) */
    private fun setListeners() {
        binding.buttonNewSentence.setOnClickListener(this)
        binding.imageviewAll.setOnClickListener(this)
        binding.imageviewHappy.setOnClickListener(this)
        binding.imageviewSunny.setOnClickListener(this)
    }
}