package com.example.storingdata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.storingdata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    var ageFromPreferences : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = this.getSharedPreferences("com.example.storingdata", Context.MODE_PRIVATE)
        ageFromPreferences = sharedPreferences.getInt("age",0)

        if (ageFromPreferences == 0)
            binding.ageText.text=""
        else
            binding.ageText.text="$ageFromPreferences"

        binding.ageSave.setOnClickListener {
            ageSave(view)
        }

        binding.ageDelete.setOnClickListener {
            ageDelete(view)
        }

    }

    fun ageSave(view: View) {
        //SharedPreferences
        val age = binding.ageEdit.text.toString().toIntOrNull()
        if (age != null) {
            binding.ageText.text = "$age"
            sharedPreferences.edit().putInt("age", age).apply()
        } else
            binding.ageText.text = ""
    }

    fun ageDelete(view: View) {
        if (ageFromPreferences != 0) {
            binding.ageText.text = "$ageFromPreferences"
            sharedPreferences.edit().clear().apply()
            binding.ageText.text = ""
        }
    }
}