package com.example.kotlin_spring_prac.blog.controller;

import com.example.kotlin_spring_prac.blog.dto.BlogDto;
import com.example.kotlin_spring_prac.blog.service.BlogService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebFluxTest(BlogController.class)
class BlogControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private BlogService blogService;

    @Test
    @DisplayName("[Kakao 블로그 검색 API 응답 확인]")
    void searchTest() {
        BlogDto blogDto = new BlogDto("kotlin", "ACCURACY", 2, 3);
        String mockResponse =
                "{\"documents\":[],\"meta\":{\"total_count\":0,\"pageable_count\":0,\"is_end\":true}}";

        when(blogService.searchKakao(any(BlogDto.class)))
                .thenReturn(Mono.just(mockResponse));

        webTestClient.post()
                .uri("/api/blog")
                .bodyValue(blogDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(mockResponse);

        verify(blogService, times(1)).searchKakao(any(BlogDto.class));
    }

}
