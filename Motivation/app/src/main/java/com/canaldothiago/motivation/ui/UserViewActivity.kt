package com.canaldothiago.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.canaldothiago.motivation.R
import com.canaldothiago.motivation.databinding.ActivityUserViewBinding
import com.canaldothiago.motivation.helpers.SecurityPreferences

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
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save) {
            handleSave()
        }
    }
    /** Valida e salva o nome do usuário no shared preferences. */
    private fun handleSave() {
        val name = binding.edittextUsername.text.toString()

        if (name.isEmpty()) {
            Toast.makeText(this, R.string.error_username_empty, Toast.LENGTH_SHORT).show()
            return
        }
        securityPreferences.storeString("username", name)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setListeners() {
        binding.buttonSave.setOnClickListener(this)
    }
}