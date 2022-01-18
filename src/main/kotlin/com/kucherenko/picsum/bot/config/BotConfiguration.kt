package com.kucherenko.picsum.bot.config

import com.kucherenko.picsum.bot.UserState
import com.kucherenko.picsum.bot.handler.UpdateHandler
import com.kucherenko.picsum.bot.handler.calback.GoodbyeCallbackHandler
import com.kucherenko.picsum.bot.handler.calback.RepeatPredictionCallbackHandler
import com.kucherenko.picsum.bot.handler.command.HelpCommandHandler
import com.kucherenko.picsum.bot.handler.command.StartCommandHandler
import com.kucherenko.picsum.bot.handler.message.GetPredictionHandler
import com.kucherenko.picsum.service.PredictionService
import com.kucherenko.picsum.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BotConfiguration {

    @Bean
    fun messageHandlers(
        userService: UserService,
        predictionService: PredictionService
    ): Map<UserState, UpdateHandler> {
        return mapOf(
            UserState.WRITING_COLOR to GetPredictionHandler(userService, predictionService),
            UserState.ONBOARDING to StartCommandHandler(userService)
        )
    }

    @Bean
    fun commandHandlers(userService: UserService): Map<String, UpdateHandler> {
        return mapOf(
            "start" to StartCommandHandler(userService),
            "help" to HelpCommandHandler()
        )
    }

    @Bean
    fun queryCallBackHandlers(
        userService: UserService,
    ): Map<String, UpdateHandler> {
        return mapOf(
            "repeat_prediction" to RepeatPredictionCallbackHandler(userService),
            "goodbye" to GoodbyeCallbackHandler(userService)
        )
    }
}