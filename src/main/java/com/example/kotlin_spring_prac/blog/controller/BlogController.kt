package com.example.kotlin_spring_prac.blog.controller

import com.example.kotlin_spring_prac.blog.dto.BlogDto
import com.example.kotlin_spring_prac.blog.entity.WordCount
import com.example.kotlin_spring_prac.blog.service.BlogService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RequestMapping("/api/blog")
@RestController
class BlogController(
    val blogService: BlogService
) {
    @PostMapping("")
    fun search(@RequestBody @Valid blogDto: BlogDto): Mono<String> {
        return blogService.searchKakao(blogDto)
    }

    @GetMapping("/rank")
    fun searchWordRank(): List<WordCount> = blogService.searchWordRank()
}