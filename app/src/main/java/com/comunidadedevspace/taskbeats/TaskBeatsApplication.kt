package com.comunidadedevspace.taskbeats

import android.app.Application
import androidx.room.Room
import com.comunidadedevspace.taskbeats.data.local.AppDataBase

class TaskBeatsApplication : Application() {

    private lateinit var database: AppDataBase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext, AppDataBase::class.java, "taskbeats-database"
        ).build()
    }

    fun getAppDatabase( ): AppDataBase
    {
        return database
    }
}