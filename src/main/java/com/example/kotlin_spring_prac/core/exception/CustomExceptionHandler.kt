package com.example.kotlin_spring_prac.core.exception

import com.example.kotlin_spring_prac.core.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(InvalidInputException::class)
    protected fun invalidInputException(ex: InvalidInputException): ResponseEntity<ErrorResponse> {
        val errors = ErrorResponse(ex.message ?: "Not Exception Message")
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }
}