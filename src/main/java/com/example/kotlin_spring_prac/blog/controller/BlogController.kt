package com.example.kotlin_spring_prac.blog.controller;

import com.example.kotlin_spring_prac.blog.dto.BlogDto;
import com.example.kotlin_spring_prac.blog.entity.WordCount
import com.example.kotlin_spring_prac.blog.service.BlogService;
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RequestMapping("/api/blog")
@RestController
class BlogController(
    val blogService: BlogService
) {
    @GetMapping("")
    fun search(
        @RequestParam query: String,
        @RequestParam sort: String,
        @RequestParam page: Int,
        @RequestParam size: Int
    ): Mono<String> {
        val blogDto = BlogDto(query, sort, page, size)
        return blogService.searchKakao(blogDto)
    }

    @GetMapping("/rank")
    fun searchWordRank(): List<WordCount> = blogService.searchWordRank()
}