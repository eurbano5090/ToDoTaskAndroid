package com.android.todolist

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import com.android.todolist.core.navigation.AppNavigation
import com.android.todolist.ui.theme.ToDoListTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val userPrefersDark = isSystemInDarkTheme()
            ToDoListTheme(darkTheme = userPrefersDark) {
                AppNavigation()
            }
        }
    }
}

