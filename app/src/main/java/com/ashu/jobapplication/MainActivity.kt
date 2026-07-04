package com.ashu.jobapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ashu.jobapplication.UserInterface.Home
import com.ashu.jobapplication.ui.theme.JobApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JobApplicationTheme {
                Home()
            }
        }
    }
}
