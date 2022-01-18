package com.kucherenko.picsum.service

import com.kucherenko.picsum.bot.BaseColor
import com.kucherenko.picsum.bot.PredictionType
import com.kucherenko.picsum.bot.exception.ColorNotFoundException
import com.kucherenko.picsum.bot.exception.DescriptionNotFoundException
import com.kucherenko.picsum.bot.exception.PredictionNotFoundException
import com.kucherenko.picsum.entity.Color
import com.kucherenko.picsum.repository.ColorDescriptionRepository
import com.kucherenko.picsum.repository.ColorRepository
import com.kucherenko.picsum.repository.PredictionRepository
import org.jsoup.Connection
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

@Service
class PredictionService(
    private val predictionRepository: PredictionRepository,
    private val colorRepository: ColorRepository,
    private val colorDescriptionRepository: ColorDescriptionRepository
) {

    fun getPredictionsForColor(colorName: String): String {
        val color = findColorByName(colorName)
        val colorNameCapitalized = color.name?.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        return "$colorNameCapitalized ${getColorDescription(color)} ${getRandomFirstPrediction()} ${getRandomSecondPrediction()}"
    }

    fun getColorDescription(color: Color): String {
        val descriptions = colorDescriptionRepository.findAllByBaseColor(color.baseColor ?: BaseColor.BLUE)
        if (descriptions.isEmpty()) {
            throw DescriptionNotFoundException("Can't find description for base color ${color.baseColor}", null)
        }
        val randomIndex = Random.nextInt(descriptions.size)
        return descriptions[randomIndex].text
    }


    private fun findColorByName(colorName: String): Color {
        val processedName = colorName.lowercase(Locale.getDefault())
            .replace("ё", "е")

        if (processedName.length < 4) {
            throw ColorNotFoundException("Can't find color with name $processedName")
        }

        return colorRepository.findByHexCodeContaining(colorName.uppercase())
            ?: colorRepository.findAllByName(processedName)
            ?: getColorByNotAccurateName(processedName)
    }

    private fun getColorByNotAccurateName(processedName: String): Color {
        val parts = processedName.split(" ", "-")
        val grainFiltered = colorRepository.findAll()
            .filter {
                parts.any { p -> it.name?.contains(p) == true }
            }

        if (grainFiltered.isEmpty()) {
            throw ColorNotFoundException("Can't find color with name $processedName")
        }
        var fineFiltered = listOf<Color>()

        if (grainFiltered.size > 1) {
            fineFiltered = grainFiltered.filter {
                parts.all { p -> it.name?.contains(p) == true }
            }
        }
        if (fineFiltered.isEmpty()) {
            return grainFiltered.first()
        }
        return fineFiltered.first()
    }


    private fun getRandomFirstPrediction(): String {
        val preds = predictionRepository.findAllByType(PredictionType.FIRST_PREDICTION)
        if (preds.isEmpty()) {
            throw PredictionNotFoundException(
                "Can't find any prediction of type ${PredictionType.FIRST_PREDICTION}}",
                null
            )
        }
        val randomIndex = Random.nextInt(preds.size)
        return preds[randomIndex].text
    }

    fun getRandomSecondPrediction(): String {
        val preds = predictionRepository.findAllByType(PredictionType.SECOND_PREDICTION)
        if (preds.isEmpty()) {
            throw PredictionNotFoundException(
                "Can't find any prediction of type ${PredictionType.SECOND_PREDICTION}}",
                null
            )
        }
        val randomIndex = Random.nextInt(preds.size)
        return preds[randomIndex].text
    }
}

