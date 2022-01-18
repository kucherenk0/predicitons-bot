package com.kucherenko.picsum.bot.handler.command

import com.kucherenko.picsum.bot.START_MESSAGE
import com.kucherenko.picsum.bot.UserState
import com.kucherenko.picsum.bot.handler.UpdateHandler
import com.kucherenko.picsum.entity.UserEntity
import com.kucherenko.picsum.service.UserService
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

class StartCommandHandler(
    private val userService: UserService
) : UpdateHandler {

    override fun handle(update: Update, user: UserEntity, sender: AbsSender) {
        userService.addMessageToHistoryFromUser(user, update.message.text)

        sender.execute(
            SendMessage(
                user.chatId,
                START_MESSAGE
            ).enableMarkdown(true)
        )
        userService.addMessageToHistoryFromBot(user, START_MESSAGE)

        userService.updateState(user.id, UserState.WRITING_COLOR)
    }
}