package com.ashu.jobapplication.UserInterface

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashu.jobapplication.R
import com.ashu.jobapplication.ViewModel.JobViewModel

@Composable
fun Home()
{
    val navController = rememberNavController()
    val jobviewModel: JobViewModel= viewModel()
    var title by remember { mutableStateOf("Jobs") }
    Scaffold(
        topBar = {
            TopAppBarX(title = title)
        },
        floatingActionButton = {FloatingButton {  }}
    ) {paddingValues ->
        NavHost(navController, startDestination = "Home")
        {
            composable("Home")
            {
                JobScreen(Modifier.padding(paddingValues),jobviewModel) { }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarX(title:String)
{
    TopAppBar(title = { Text(title) },
        navigationIcon = {},
        colors = TopAppBarDefaults.topAppBarColors(colorResource( R.color.custom)))
}

@Composable
fun FloatingButton(onClick: () -> Unit)
{
    FloatingActionButton(onClick = onClick)
    {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add" )
    }
}