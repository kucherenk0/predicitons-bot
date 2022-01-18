package com.kucherenko.picsum.bot.config.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class BotProperties(
    val token: String,
    @Value("dekadance_colors_bot")
    val botUserName: String
)