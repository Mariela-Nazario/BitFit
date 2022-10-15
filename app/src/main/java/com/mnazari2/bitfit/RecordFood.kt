package com.mnazari2.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RecordFood : AppCompatActivity() {
    lateinit var food_Name: String
    lateinit var food_Cal: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordfood)
        val btn = findViewById(R.id.btn_record) as Button
        var food_item = findViewById(R.id.food_input) as EditText
        var food_cal= findViewById(R.id.calorie_input) as EditText

        btn.setOnClickListener() {
            food_Name = food_item.text.toString()
            food_Cal = food_cal.text.toString()
            val intent = Intent(this, MainActivity::class.java)
             intent.putExtra("name",food_Name)
            intent.putExtra("calories",food_Cal)
            this.startActivity(intent)
            finish()
        }

    }
}
