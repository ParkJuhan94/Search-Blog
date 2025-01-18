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

@Service
class BlogService(
    val wordRepository: WordRepository
) {
    @Value("\${REST_API_KEY}")
    lateinit var restApiKey: String

    // Kakao 블로그 검색 API를 호출하는 함수.
    fun searchKakao(blogDto: BlogDto): String? {
        // WebClient를 사용해 Kakao API에 요청을 보냄.
        val webClient = WebClient
            .builder()
            .baseUrl("https://dapi.kakao.com") // Kakao API의 기본 URL.
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // JSON 타입의 헤더 설정.
            .build()

        // GET 요청을 생성하고 필요한 파라미터와 헤더를 설정.
        val response = webClient
            .get()
            .uri {
                it.path("/v2/search/blog") // 블로그 검색 API 경로.
                    .queryParam("query", blogDto.query) // 검색어 파라미터.
                    .queryParam("sort", blogDto.sort) // 정렬 기준 파라미터.
                    .queryParam("page", blogDto.page) // 페이지 번호 파라미터.
                    .queryParam("size", blogDto.size) // 한 페이지에 표시할 문서 수.
                    .build()
            }
            .header("Authorization", "KakaoAK $restApiKey") // 인증 헤더에 API 키 추가.
            .retrieve() // 응답을 받음.
            .bodyToMono<String>() // 응답을 String으로 변환.

        // 검색어 순위 처리하기
        val lowQuery: String = blogDto.query.lowercase()
        val word: WordCount = wordRepository.findById(lowQuery).orElse(WordCount(lowQuery))
        word.cnt++

        wordRepository.save(word)

        return response.block() // 응답을 블록하고 반환. 이 부분은 비동기 프로세스를 동기적으로 처리.
    }

    fun searchWordRank(): List<WordCount> = wordRepository.findTop10ByOrderByCntDesc()
}