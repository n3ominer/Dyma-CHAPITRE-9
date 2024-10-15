package com.example.dymachap9

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dymachap9.models.TodoModel
import com.example.dymachap9.viewmodels.TodoViewModel

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    lateinit var todoViewModel: TodoViewModel

    private val todos: List<TodoModel> = listOf(
        TodoModel("06/10/2024", "Faire du sport", false),
        TodoModel("26/10/2024", "Lire un livre", true),
        TodoModel("11/10/2024", "Sortir les poubelles", false),
        TodoModel("31/10/2024", "Finir la formation Dyma", true),
        TodoModel("12/10/2024", "Apprendre à coder", false),
        TodoModel("29/10/2024", "Réparer le téléphone", true),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.todoViewModel = TodoViewModel(this)

        initSharedPreferences()
        Toast.makeText(this, getUserName(), Toast.LENGTH_LONG).show()

        Log.d("Shared preferences", sharedPreferences.all.toString())

        saveUserDataInSp("Titi", "Tutu")
        Log.d("Shared preferences", sharedPreferences.all.toString())
        Toast.makeText(this, getUserName(), Toast.LENGTH_LONG).show()


        writeInLocalFIle()
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

    fun writeInLocalFIle() {
        val fileContent = todos.map {
            "${it.data} || ${it.description} || La tâche est terminée: ${if(it.isFinished) "Vrai" else "False"}\n"
        }.toString()

        val contentMetaData = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "local_text_todo_file")
            put(MediaStore.MediaColumns.MIME_TYPE, "text/plain")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS)
        }

        val uri = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentMetaData)
        uri?.let {
            contentResolver.openOutputStream(it).use { outputStream ->
                outputStream?.write(fileContent.toByteArray())
            }
        }

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