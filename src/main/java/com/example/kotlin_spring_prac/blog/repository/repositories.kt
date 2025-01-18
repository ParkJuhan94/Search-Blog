package com.example.kotlin_spring_prac.blog.repository

import com.example.kotlin_spring_prac.blog.entity.WordCount
import org.springframework.data.repository.CrudRepository

interface WordRepository : CrudRepository<WordCount, String> {
    fun findTop10ByOrderByCntDesc(): List<WordCount>
}