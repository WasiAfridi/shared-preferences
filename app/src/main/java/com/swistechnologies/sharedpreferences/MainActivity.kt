package com.swistechnologies.sharedpreferences

import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.swistechnologies.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreference : SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreference = getSharedPreferences("my_SharedPreference", MODE_PRIVATE)
        editor = sharedPreference.edit()

        binding.btnSave.setOnClickListener {
            finish()
        }
    }


    override fun onPause() {
        super.onPause()
        if(binding.rememberMe.isChecked){
            val name = binding.txtName.text.toString()
            val email = binding.txtEmail.text.toString()
            editor.apply() {
                putString("sp_name", name)
                putString("sp_email", email)
                commit()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        val name = sharedPreference.getString("sp_name", null)
        val email = sharedPreference.getString("sp_email", null)
        binding.txtName.setText(name)
        binding.txtEmail.setText(email)
    }

}