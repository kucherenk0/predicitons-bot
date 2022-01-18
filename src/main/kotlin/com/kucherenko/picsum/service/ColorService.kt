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
class ColorService(
    private val colorRepository: ColorRepository,
) {


    @Scheduled(fixedRate = 6000000000)
    fun setAllColorNamesToLowerCase(){
        val colors = colorRepository.findAll();

        colors.forEach{
             it.name = it.name?.lowercase(Locale.getDefault())?.replace("ё", "е")
            colorRepository.save(it)
        }
    }

  //  @Scheduled(fixedRate = 6000000000)
    fun enrichDbWithColors() {

        val elements = Jsoup.connect("https://colorscheme.ru/color-names.html")
            .timeout(10000)
            .method(Connection.Method.GET)
            .execute()
            .parse()

        val colors = elements.getElementById("color-names")
            ?.select("tr")
            ?.takeLast(1017)
            ?.map {
                getColorFromElement(it ?: throw RuntimeException("null"))
            }
        colors?.forEach {
            colorRepository.save(it)
        }
    }

    private fun getColorFromElement(element: Element): Color {
        val fields = element.select("td")
        val color = Color(
            name = fields[1].text(),
            hexCode = fields[2].text(),
            redComponent = fields[3].text().toInt(),
            greenComponent = fields[4].text().toInt(),
            blueComponent = fields[5].text().toInt()
        )
        color.baseColor = getBaseColor(color)
        return color
    }

    private fun getBaseColor(color: Color): BaseColor {
        baseColorsMap.forEach{
           it.value.forEach{ colorStr ->
               if(color.name?.lowercase(Locale.getDefault())?.contains(colorStr) == true){
                   return it.key
               }
           }}

        return baseColorsList.minByOrNull { baseColor -> getColorDistance(baseColor, color) }!!
            .baseColor ?: throw RuntimeException("Can't get Base color")
    }


    private fun getColorDistance(c1: Color, c2: Color): Double {
        val r1 = c1.redComponent?.toDouble() ?: 0.0
        val g1 = c1.greenComponent?.toDouble() ?: 0.0
        val b1 = c1.blueComponent?.toDouble() ?: 0.0
        val r2 = c2.redComponent?.toDouble() ?: 0.0
        val g2 = c2.greenComponent?.toDouble() ?: 0.0
        val b2 = c2.blueComponent?.toDouble() ?: 0.0


        return sqrt(
            r1.minus(r2).pow(2) +
                    g1.minus(g2).pow(2) +
                    b1.minus(b2).pow(2)
        )
    }

    val baseColorsMap = mapOf(
        BaseColor.PINK to listOf("розовый"),
        BaseColor.PURPLE to listOf("фиолетовый", "фиолет"),
        BaseColor.BLACK to listOf("чёрный"),
        BaseColor.BLUE to listOf("голубой", "синий"),
        BaseColor.GREY to listOf("серый"),
        BaseColor.GREEN to listOf("розовый"),
        BaseColor.YELLOW to listOf("желтый"),
        BaseColor.RED to listOf("красный", "алый"),
        BaseColor.ORANGE to listOf("оранжевый", "рыжий")
    )

    val baseColorsList = listOf(
        Color(
            name = "белый",
            baseColor = BaseColor.WHITE,
            hexCode = null,
            redComponent = 255,
            greenComponent = 255,
            blueComponent = 255
        ),Color(
            name = "белый",
            baseColor = BaseColor.WHITE,
            hexCode = null,
            redComponent = 245,
            greenComponent = 245,
            blueComponent = 220
        ),
        Color(
            name = "розовый",
            baseColor = BaseColor.PINK,
            hexCode = null,
            redComponent = 255,
            greenComponent = 20,
            blueComponent = 147
        ), Color(
            name = "зеленый",
            baseColor = BaseColor.GREEN,
            hexCode = null,
            redComponent = 0,
            greenComponent = 255,
            blueComponent = 0
        ), Color(
            name = "оранжевый",
            baseColor = BaseColor.ORANGE,
            hexCode = null,
            redComponent = 255,
            greenComponent = 165,
            blueComponent = 0
        ), Color(
            name = "красный",
            baseColor = BaseColor.RED,
            hexCode = null,
            redComponent = 255,
            greenComponent = 0,
            blueComponent = 0
        ), Color(
            name = "желтый",
            baseColor = BaseColor.YELLOW,
            hexCode = null,
            redComponent = 255,
            greenComponent = 0,
            blueComponent = 0
        ), Color(
            name = "желтый",
            baseColor = BaseColor.YELLOW,
            hexCode = null,
            redComponent = 255,
            greenComponent = 250,
            blueComponent = 205
        ), Color(
            name = "желтый",
            baseColor = BaseColor.YELLOW,
            hexCode = null,
            redComponent = 189,
            greenComponent = 183,
            blueComponent = 107
        ), Color(
            name = "желтый",
            baseColor = BaseColor.YELLOW,
            hexCode = null,
            redComponent = 255,
            greenComponent = 228,
            blueComponent = 181
        ), Color(
            name = "серый",
            baseColor = BaseColor.GREY,
            hexCode = null,
            redComponent = 128,
            greenComponent = 128,
            blueComponent = 128
        ), Color(
            name = "чёрный",
            baseColor = BaseColor.BLACK,
            hexCode = null,
            redComponent = 0,
            greenComponent = 0,
            blueComponent = 0
        ), Color(
            name = "",
            baseColor = BaseColor.BLUE,
            hexCode = null,
            redComponent = 0,
            greenComponent = 0,
            blueComponent = 255
        ), Color(
            name = "",
            baseColor = BaseColor.BLUE,
            hexCode = null,
            redComponent = 0,
            greenComponent = 255,
            blueComponent = 255
        ), Color(
            name = "",
            baseColor = BaseColor.BLUE,
            hexCode = null,
            redComponent = 70,
            greenComponent = 130,
            blueComponent = 180
        ), Color(
            name = "",
            baseColor = BaseColor.BLUE,
            hexCode = null,
            redComponent = 25,
            greenComponent = 25,
            blueComponent = 112
        ), Color(
            name = "",
            baseColor = BaseColor.PURPLE,
            hexCode = null,
            redComponent = 128,
            greenComponent = 0,
            blueComponent = 128
        ), Color(
            name = "",
            baseColor = BaseColor.PURPLE,
            hexCode = null,
            redComponent = 128,
            greenComponent = 0,
            blueComponent = 128
        ), Color(
            name = "",
            baseColor = BaseColor.PURPLE,
            hexCode = null,
            redComponent = 75,
            greenComponent = 0,
            blueComponent = 130
        ), Color(
            name = "",
            baseColor = BaseColor.PURPLE,
            hexCode = null,
            redComponent = 230,
            greenComponent = 230,
            blueComponent = 250
        ), Color(
            name = "",
            baseColor = BaseColor.PURPLE,
            hexCode = null,
            redComponent = 72,
            greenComponent = 61,
            blueComponent = 139
        )
    )

}

