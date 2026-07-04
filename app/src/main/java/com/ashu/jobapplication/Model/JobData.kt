package com.ashu.jobapplication.Model

data class JobData(
    val postProfile:String,
    val postDesc:String,
    val reqExperience: Int,
    val postTechStack:List<String>
)

data class JobPosts(
    val jobPosts:List<JobData>
)
data class JobResponse(
    val _embedded : JobPosts
)
