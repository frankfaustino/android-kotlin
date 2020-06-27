package com.ff.colormyviews

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        val boxOneText = findViewById<TextView>(R.id.box_one_text)
        val boxTwoText = findViewById<TextView>(R.id.box_two_text)
        val boxThreeText = findViewById<TextView>(R.id.box_three_text)
        val boxFourText = findViewById<TextView>(R.id.box_four_text)
        val boxFiveText = findViewById<TextView>(R.id.box_five_text)

        val redButton = findViewById<Button>(R.id.red_button)
        val yellowButton = findViewById<Button>(R.id.yellow_button)
        val greenButton = findViewById<Button>(R.id.green_button)

        val rootConstraintLayout = findViewById<View>(R.id.constraint_layout)

        val clickableViews: List<View> = listOf(
            boxOneText,
            boxTwoText,
            boxThreeText,
            boxFourText,
            boxFiveText,
            redButton,
            yellowButton,
            greenButton,
            rootConstraintLayout
        )

        for (item in clickableViews) {
            item.setOnClickListener { makeColored(it) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun makeColored(view: View) {
        when (view.id) {
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)
            R.id.box_three_text -> view.setBackgroundColor(Color.BLUE)
            R.id.box_four_text -> view.setBackgroundColor(Color.MAGENTA)
            R.id.box_five_text -> view.setBackgroundColor(Color.BLUE)
            R.id.red_button -> box_three_text.setBackgroundColor(getColor(R.color.my_red))
            R.id.yellow_button -> box_four_text.setBackgroundColor(getColor(R.color.my_yellow))
            R.id.green_button -> box_five_text.setBackgroundColor(getColor(R.color.my_green))

            else -> view.setBackgroundResource(R.drawable.one)
        }
    }
}