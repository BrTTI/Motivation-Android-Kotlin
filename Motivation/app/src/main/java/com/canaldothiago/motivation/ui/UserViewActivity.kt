package com.canaldothiago.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.canaldothiago.motivation.helpers.MotivationConstants
import com.canaldothiago.motivation.R
import com.canaldothiago.motivation.databinding.ActivityUserViewBinding
import com.canaldothiago.motivation.repository.SecurityPreferences

class UserViewActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserViewBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityUserViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        securityPreferences = SecurityPreferences(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
        verifyUserName()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun verifyUserName() {
        val name = securityPreferences.getString(MotivationConstants.KEY.USERNAME)
        if (name.isNotEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    /** Valida e salva o nome do usuário no shared preferences. */
    private fun handleSave() {
        val name = binding.edittextUsername.text.toString()
        if (name.isEmpty()) {
            Toast.makeText(this, R.string.error_username_empty, Toast.LENGTH_SHORT).show()
            return
        }
        securityPreferences.storeString(MotivationConstants.KEY.USERNAME, name)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener(this)
    }
}