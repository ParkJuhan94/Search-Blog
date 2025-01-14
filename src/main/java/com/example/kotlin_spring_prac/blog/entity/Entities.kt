package com.example.kotlin_spring_prac.blog.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class WordCount(
    @Id val word: String,
    val cnt: Int = 0
)