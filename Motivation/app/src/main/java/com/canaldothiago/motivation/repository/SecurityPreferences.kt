package com.canaldothiago.motivation.repository

import android.content.Context
import androidx.core.content.edit

class SecurityPreferences(context: Context) {

    private val shared = context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        shared.edit { putString(key, value) }
    }

    fun getString(key: String): String {
        return shared.getString(key, "") ?: ""
    }
}