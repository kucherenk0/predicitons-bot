package com.kucherenko.picsum.repository

import com.kucherenko.picsum.bot.PredictionType
import com.kucherenko.picsum.entity.Prediction
import com.kucherenko.picsum.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PredictionRepository : JpaRepository<Prediction, Long> {
    fun findAllByType(type: PredictionType): List<Prediction>
}