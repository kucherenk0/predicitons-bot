package com.kucherenko.picsum.bot.exception

open class BotException(
    message: String,
    cause: Throwable?
) : RuntimeException(message, cause) {
}

class UndefinedBehaviourException(
    message: String,
    cause: Throwable? = null
) : BotException(message, cause)

class InvalidUserStateException(
    message: String,
    cause: Throwable? = null
) : BotException(message, cause)

class DataNotFoundException(
    message: String,
    cause: Throwable? = null
) : BotException(message, cause)

class ColorNotFoundException(
    message: String,
    cause: Throwable? = null
) : BotException(message, cause)

class DescriptionNotFoundException(
    message: String,
    cause: Throwable? = null
) : BotException(message, cause)

class PredictionNotFoundException(
    message: String,
    cause: Throwable? = null
) : BotException(message, cause)

