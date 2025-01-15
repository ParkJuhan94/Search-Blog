package com.example.kotlin_spring_prac.core.exception

class InvalidInputException(
    message: String = "Invalid Input"
) : RuntimeException(message)