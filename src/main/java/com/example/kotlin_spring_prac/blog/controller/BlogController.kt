package com.example.kotlin_spring_prac.blog.controller;

import com.example.kotlin_spring_prac.blog.dto.BlogDto;
import com.example.kotlin_spring_prac.blog.entity.WordCount
import com.example.kotlin_spring_prac.blog.service.BlogService;
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/blog")
@RestController
class BlogController(
    val blogService: BlogService
) {
    @GetMapping("")
    fun search(@RequestBody @Valid blogDto: BlogDto): String? {
        return blogService.searchKakao(blogDto)
    }

    @GetMapping("/rank")
    fun searchWordRank(): List<WordCount> = blogService.searchWordRank()
}