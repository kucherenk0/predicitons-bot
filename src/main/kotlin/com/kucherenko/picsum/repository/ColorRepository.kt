package com.kucherenko.picsum.repository

import com.kucherenko.picsum.entity.Color
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ColorRepository : JpaRepository<Color, Long> {
    fun findAllByName(name: String): Color?
    fun findByHexCodeContaining(name: String): Color?
}