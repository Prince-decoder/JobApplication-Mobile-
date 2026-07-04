package com.ashu.jobapplication.UserInterface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashu.jobapplication.Model.JobData
import com.ashu.jobapplication.R
import com.ashu.jobapplication.ViewModel.JobViewModel

@Composable
fun JobScreen(modifier: Modifier = Modifier,jobViewModel: JobViewModel,onJobClick:()-> Unit) {

    val state by jobViewModel.status

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.DarkBackground))
    ) {
        when {
            state.loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = colorResource(R.color.BrightRed)
                )
            }
            state.error != null -> {
                Text(
                    text = state.error.toString(),
                    color = colorResource(R.color.BrightRed),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
            state.list.isEmpty() -> {
                // Added an empty state check
                Text(
                    text = "No jobs available at the moment.",
                    color = Color.LightGray,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                JobDisplay(jobs = state.list)
            }
        }
    }
}

@Composable
fun JobDisplay(jobs: List<JobData>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.DarkBackground)),
        // Added padding around the grid and spacing between the cards
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(jobs) { job ->
            JobCard(job)
        }
    }
}

@Composable
fun JobCard(job: JobData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            // Adds a subtle lighter background to make the card pop off the dark screen
            .background(Color.White.copy(alpha = 0.05f))
            .border(
                width = 1.dp,
                color = colorResource(R.color.custom).copy(alpha = 0.5f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp) // Inner padding so text doesn't touch the edges
    ) {
        // Job Title
        Text(
            text = job.postProfile ?: "Untitled Position",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color.Cyan,
            letterSpacing = 0.5.sp,
            maxLines = 1, // Keep title to a single line in a grid
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Job Description
        Text(
            text = job.postDesc ?: "No description provided.",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color.LightGray, // Light gray reads better for body text than cyan
            lineHeight = 18.sp,
            maxLines = 3, // Constrain height so the grid stays balanced
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Push the button to the bottom if cards have uneven text lengths
        Spacer(modifier = Modifier.weight(1f, fill = false))

        // Primary Button
        Button(
            onClick = { /* Handle click event */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.BloodRed),
                contentColor = Color.White // White pops better against a Blood Red background
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth() // Spanning the button across the card looks great on grids
        ) {
            Text(
                text = "VIEW JOB",
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                fontSize = 12.sp,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}