package com.kucherenko.picsum.bot.handler.message

import com.kucherenko.picsum.bot.EGGPLANT_MESSAGE
import com.kucherenko.picsum.bot.EGGPLANT_STRING
import com.kucherenko.picsum.bot.UserState
import com.kucherenko.picsum.bot.getPredictionsMarkup
import com.kucherenko.picsum.bot.handler.UpdateHandler
import com.kucherenko.picsum.entity.UserEntity
import com.kucherenko.picsum.service.PredictionService
import com.kucherenko.picsum.service.UserService
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender
import java.util.*

class GetPredictionHandler(
    private val userService: UserService,
    private val predictionService: PredictionService
) : UpdateHandler {

    override fun handle(update: Update, user: UserEntity, sender: AbsSender) {
        userService.addMessageToHistoryFromUser(user, update.message.text);

        val predictionText = predictionService.getPredictionsForColor(update.message.text);

        if(update.message.text.lowercase(Locale.getDefault()).contains(EGGPLANT_STRING)){
            sender.execute(
                SendMessage(
                    user.chatId,
                    EGGPLANT_MESSAGE
                )
            )
        }
        sender.execute(
            SendMessage(
                user.chatId,
                predictionText
            ).setReplyMarkup(getPredictionsMarkup())
        )

        userService.addMessageToHistoryFromBot(user, predictionText)
        userService.updateState(user.id, UserState.WRITING_COLOR)
    }
}
