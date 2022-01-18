package com.kucherenko.picsum.bot.exception

import org.telegram.telegrambots.meta.api.objects.Update
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.bots.AbsSender

@Service
class GlobalExceptionHandler {

    private val COLOR_NOT_FOUND_EXCEPTION_MESSAGE = "Хмм..\uD83E\uDD14 я не знаю такой цвет :( Попробуй еще раз!"
    private val DESCRIPTION_NOT_FOUND_EXCEPTION_MESSAGE = "Хмм..\uD83E\uDD14 для этого цвета я не нашел предсказаний:( Попробуй еще раз!"
    private val PREDICTION_NOT_FOUND_EXCEPTION_MESSAGE = "Упс..\uD83E\uDD14 кажется, мои предсказания закончились:( Попробуй еще раз!"
    private val EXTERNAL_ERROR_MESSAGE = "Упс.. что-то пошло не так. Попробуй еще раз!"

    //TODO: add logging
    fun handle(exception: Exception, update: Update, sender: AbsSender) {
        when(exception){
            is ColorNotFoundException -> sendErrorMessage(update, sender, COLOR_NOT_FOUND_EXCEPTION_MESSAGE)
            is DescriptionNotFoundException -> sendErrorMessage(update, sender, DESCRIPTION_NOT_FOUND_EXCEPTION_MESSAGE)
            is PredictionNotFoundException -> sendErrorMessage(update, sender, PREDICTION_NOT_FOUND_EXCEPTION_MESSAGE)
            else -> sendErrorMessage(update, sender, EXTERNAL_ERROR_MESSAGE)
        }

        throw exception
    }


    private fun sendErrorMessage(update: Update, sender: AbsSender, text: String){
        sender.execute(
            SendMessage(
                update.message.chatId,
                text
            )
        )
    }
}