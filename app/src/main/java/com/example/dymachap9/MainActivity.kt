package com.example.dymachap9

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initSharedPreferences()
        Toast.makeText(this, getUserName(), Toast.LENGTH_LONG).show()

        Log.d("Shared preferences", sharedPreferences.all.toString())

        saveUserDataInSp("Titi", "Tutu")
        Log.d("Shared preferences", sharedPreferences.all.toString())
        Toast.makeText(this, getUserName(), Toast.LENGTH_LONG).show()
    }

    private fun initSharedPreferences() {
        this.sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    }

    private fun saveUserDataInSp(name: String, lastname: String) {
        this.sharedPreferences.edit()
            .putString("USER_NAME", name)
            .putString("USER_LAST_NAME", lastname)
            .apply()
    }

    private fun saveDataIntoSp(data: String, dataName: String) {
        this.sharedPreferences.edit()
            .putString(dataName, data)
            .apply()
    }

    private fun getUserName(): String {
        return this.sharedPreferences.getString("USER_NAME", "Default")!!
    }

    private fun removeUserNameFromSp(key: String) {
        this.sharedPreferences
            .edit()
            .remove(key)
            .apply()
    }

    private fun clearSp() {
        this.sharedPreferences
            .edit()
            .clear()
            .apply()
    }

}