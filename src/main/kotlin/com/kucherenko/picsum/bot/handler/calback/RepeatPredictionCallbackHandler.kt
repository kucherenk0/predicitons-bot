package com.kucherenko.picsum.bot.handler.calback

import com.kucherenko.picsum.bot.REPEAT_PREDICTION_MESSAGE
import com.kucherenko.picsum.bot.UserState
import com.kucherenko.picsum.bot.handler.UpdateHandler
import com.kucherenko.picsum.entity.UserEntity
import com.kucherenko.picsum.service.UserService
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

class RepeatPredictionCallbackHandler(
    private val userService: UserService
) : UpdateHandler {

    override fun handle(update: Update, user: UserEntity, sender: AbsSender) {
        userService.addMessageToHistoryFromUser(user, "Presses Repeat button");
        sender.execute(
            AnswerCallbackQuery()
            .setCallbackQueryId(update.callbackQuery.id))
        sender.execute(
            SendMessage(
                user.chatId,
                REPEAT_PREDICTION_MESSAGE
            ).enableMarkdown(true)
        )
        userService.addMessageToHistoryFromBot(user, REPEAT_PREDICTION_MESSAGE)

        userService.updateState(user.id, UserState.WRITING_COLOR)
    }
}