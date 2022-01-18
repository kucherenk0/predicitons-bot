package com.kucherenko.picsum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.telegram.telegrambots.ApiContextInitializer


@EnableJpaRepositories(basePackages = ["com.kucherenko.picsum.repository"])
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
class PredictionBotApplication

fun main(args: Array<String>) {
    runApplication<PredictionBotApplication>(*args) {
        ApiContextInitializer.init()
    }
}
