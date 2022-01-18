package com.kucherenko.picsum.repository

import com.kucherenko.picsum.bot.BaseColor
import com.kucherenko.picsum.entity.Color
import com.kucherenko.picsum.entity.ColorDescription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ColorDescriptionRepository : JpaRepository<ColorDescription, Long> {
    fun findAllByBaseColor(baseColor: BaseColor): List<ColorDescription>
}