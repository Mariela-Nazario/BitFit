package com.mnazari2.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val articles = mutableListOf<DisplayFoodCalories>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById(R.id.btn_addFood) as Button
        val  Rv = findViewById<RecyclerView>(R.id.Rv)
        val adapter = Adapter(articles)

        lifecycleScope.launch {
            (application as FoodAplication).db.articleDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayFoodCalories(
                        entity.food,
                        entity.calories

                    )
                }.also { mappedList ->
                    articles.clear()
                    articles.addAll(mappedList)
                    adapter.notifyDataSetChanged()
                }
            }

        }


        btn.setOnClickListener() {

            val intent = Intent(this, RecordFood::class.java)

            this.startActivity(intent)
        }

            var username = getIntent().getStringExtra("name").toString()
            var cal = getIntent().getStringExtra("calories").toString()
        if(cal != "null" && username != "null") {


            val l = foodCaloriesEntity(food = username, calories = cal)



            lifecycleScope.launch(IO) {
                //  (application as FoodAplication).db.articleDao().deleteAll()

                (application as FoodAplication).db.articleDao().insertAll(l)
            }

        }
        Rv.adapter = adapter
        Rv.layoutManager = LinearLayoutManager(this)


    }
}