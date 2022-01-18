package com.kucherenko.picsum.bot.config.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
data class BotProperties(
    @Value("2118132257:AAE0gzq8VFDqgTLYYWHmalT4WdtQSugxrD8")
    val token: String,
    @Value("dekadance_colors_bot")
    val botUserName: String
)