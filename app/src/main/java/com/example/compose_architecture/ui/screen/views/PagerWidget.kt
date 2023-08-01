package com.example.compose_architecture.ui.screen.views

import android.content.Intent
import androidx.activity.ComponentActivity
import com.example.compose_architecture.ui.activity.StartActivity


fun ComponentActivity.showPagerWidget() {
    startActivity(Intent(this, StartActivity::class.java))
    finish()
}