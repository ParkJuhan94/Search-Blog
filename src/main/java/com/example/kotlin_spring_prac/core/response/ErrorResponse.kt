package com.example.kotlin_spring_prac.core.response

data class ErrorResponse(
    val message: String,
    val errorType: String = "Invalid Argument"
)