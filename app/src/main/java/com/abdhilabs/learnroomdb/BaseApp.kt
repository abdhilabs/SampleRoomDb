package com.abdhilabs.learnroomdb

import android.app.Application
import androidx.room.Room
import com.abdhilabs.learnroomdb.db.AppDatabase

class BaseApp : Application() {

    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "TODO")
            .allowMainThreadQueries().build()
    }

}
