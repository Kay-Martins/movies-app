package com.kaycode.moviesapp

import android.app.Application
import android.content.Intent
import com.kaycode.feature.latest_films.presentation.activity.LatestFilmsActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainActivity : Application() {
    override fun onCreate() {
        super.onCreate()
        val intent = Intent(this, LatestFilmsActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        this.startActivity(intent)
    }
}