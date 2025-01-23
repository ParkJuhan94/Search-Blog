package com.example.kotlin_spring_prac.blog.service

import com.example.kotlin_spring_prac.blog.dto.BlogDto
import com.example.kotlin_spring_prac.blog.entity.WordCount
import com.example.kotlin_spring_prac.blog.repository.WordRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@Service
class BlogService(
    val wordRepository: WordRepository
) {
    @Value("\${REST_API_KEY}")
    lateinit var restApiKey: String

    // Kakao 블로그 검색 API를 호출하는 함수.
    fun searchKakao(blogDto: BlogDto): Mono<String>? {
        val webClient = WebClient
            .builder()
            .baseUrl("https://dapi.kakao.com")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

        return webClient
            .get()
            .uri {
                it.path("/v2/search/blog")
                    .queryParam("query", blogDto.query)
                    .queryParam("sort", blogDto.sort)
                    .queryParam("page", blogDto.page)
                    .queryParam("size", blogDto.size)
                    .build()
            }
            .header("Authorization", "KakaoAK $restApiKey")
            .retrieve()
            .bodyToMono<String>()
            .doOnSuccess { saveSearchQuery(blogDto.query) } // 응답 성공 시 검색어 순위 처리
    }

    // 검색어 순위 처리 함수.
    private fun saveSearchQuery(query: String) {
        val lowQuery = query.lowercase()
        val word = wordRepository.findById(lowQuery).orElse(WordCount(lowQuery))
        word.cnt++
        wordRepository.save(word)
    }

    fun searchWordRank(): List<WordCount> = wordRepository.findTop10ByOrderByCntDesc()
}