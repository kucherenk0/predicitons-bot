package com.kucherenko.picsum.config

import com.kucherenko.picsum.bot.PicsumBot
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi


@Configuration
class PredictionServiceConfiguration {

    @Bean
    fun telegramBotsApi(picsumBot: PicsumBot): TelegramBotsApi {
        val telegramBotsApi = TelegramBotsApi()
        telegramBotsApi.registerBot(picsumBot)
        return telegramBotsApi
    }

}
