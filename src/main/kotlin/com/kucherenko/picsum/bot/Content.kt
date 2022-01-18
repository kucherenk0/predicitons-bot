package com.kucherenko.picsum.bot

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton

enum class UserState() {
    ONBOARDING,
    WRITING_COLOR,
    ASKED_TO_CONTINUE
}

enum class PredictionType() {
    FIRST_PREDICTION,
    SECOND_PREDICTION
}

enum class BaseColor() {
    PINK,
    GREEN,
    ORANGE,
    RED,
    YELLOW,
    GREY,
    BLACK,
    BLUE,
    PURPLE,
    WHITE
}

val START_MESSAGE = "Привет! Рад, что ты пришел ко мне за информацией\uD83D\uDC97\n" +
        "Узнай свой цвет по этой [ссылке](yandex.ru/search/?text=цвет), отправь мне его название или номер\n" +
        "и в ответ ты получишь предсказание \uD83D\uDD2E Все тайное станет явным с помощью цвета из Яндекса\uD83E\uDE84\uD83D\uDCAB\n" +
        "Учти, что я говорю правду только в первый раз, будешь обманывать и проверять второй раз — не жди правды и от меня\uD83D\uDEF8"

val LIST_OF_COMMANDS = listOf(
    "/start",
    "/help"
)
val REPEAT_PREDICTION_MESSAGE = "Введи название цвета или его номер...\uD83D\uDD2E"

val GOODBYE_MESSAGE = "Было приятно поболтать, приходи в [наш инстаграм](https://www.instagram.com/dekadance.magazine/) 💫💗"

fun getPredictionsMarkup(): InlineKeyboardMarkup {
    val addPhotoButton = InlineKeyboardButton()
    addPhotoButton.text = "Новое предсказание\uD83D\uDD2E"
    addPhotoButton.callbackData = "repeat_prediction"

    val photoListbutton = InlineKeyboardButton()
    photoListbutton.text = "⚡️"
    photoListbutton.callbackData = "goodbye"

    val markup = InlineKeyboardMarkup()
    markup.keyboard = listOf(listOf(addPhotoButton), listOf(photoListbutton))
    return markup
}

val EGGPLANT_STRING = "баклажан"
val EGGPLANT_MESSAGE = "\uD83C\uDF46"