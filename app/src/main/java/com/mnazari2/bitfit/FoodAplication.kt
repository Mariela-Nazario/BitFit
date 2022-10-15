package com.mnazari2.bitfit

import android.app.Application

class FoodAplication : Application() {
    val db by lazy { DataBase.getInstance(this) }
}