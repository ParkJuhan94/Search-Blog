package com.example.kotlin_spring_prac.blog.dto

import com.example.kotlin_spring_prac.core.annotation.ValidEnum
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class BlogDto(
    @field:NotBlank(message = "query parameter required")
    val query: String?,

    @field:NotBlank(message = "sort parameter required")
    @field:ValidEnum(enumClass = EnumSort::class, message = "sort parameter one of ACCURACY and RECENCY")
    val sort: String?,

    @field:NotNull(message = "page parameter required")
    @field:Max(50, message = "page is more than max")
    @field:Min(1, message = "page is less than min")
    val page: Int?,

    @field:NotNull(message = "size parameter required")
    val size: Int?
) {
    private enum class EnumSort {
        ACCURACY,
        RECENCY
    }
}