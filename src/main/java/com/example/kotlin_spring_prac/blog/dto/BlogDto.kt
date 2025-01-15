package com.example.kotlin_spring_prac.blog.dto

data class BlogDto (
    val query: String,
    val sort: String,
    val page: Int,
    val size: Int
)