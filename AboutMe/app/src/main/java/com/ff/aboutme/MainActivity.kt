package com.ff.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil

import com.ff.aboutme.databinding.ActivityMainBinding

/**
 * This app demonstrates data binding between MainActivity and activity_main.xml.
 * How to remove findById, and how to display data in views using the data binding object.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Frank Faustino")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.doneButton.setOnClickListener { addNickname() }

        // binding.myName corresponds to the myName variable in activity_main.xml.
        binding.myName = myName
    }

    private fun addNickname() {
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            // invalidate all binding expressions so that they're recreated with the correct data.
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.doneButton.windowToken, 0)
    }
}