package com.kucherenko.picsum.service

import com.kucherenko.picsum.bot.UserState
import com.kucherenko.picsum.bot.exception.DataNotFoundException
import com.kucherenko.picsum.entity.UserEntity
import com.kucherenko.picsum.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class UserService(
    private val userRepository: UserRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun getByTelegramId(telegramId: String): UserEntity {
        val user = userRepository.getByTelegramId(telegramId)
            ?: throw DataNotFoundException("Can't find user with telegram_id '$telegramId'")
        if (user.deleted) {
            //TODO: change
            throw DataNotFoundException("User with telegram_id $telegramId was deleted")
        }
        return user
    }

    fun create(userEntity: UserEntity): UserEntity = userRepository.save(userEntity)

    fun update(userEntity: UserEntity): UserEntity = userRepository.save(userEntity)

    fun updateState(id: Long?, userState: UserState) {
        if (id != null) {
            val user = userRepository.getById(id)
            user.userState = userState
            userRepository.save(user)
        } else
            throw IllegalArgumentException("Id must be not null")
    }

    fun addMessageToHistoryFromUser(userEntity: UserEntity, message: String){
       try { val messageHistory = userEntity.messageHistory + "\n${userEntity.telegramId}(${LocalDateTime.now()}): \n $message"
        userEntity.messageHistory = messageHistory
        userRepository.save(userEntity);

       } catch (e: Exception){
           logger.error("Error while saving message history from user ${userEntity.id}", e.localizedMessage)
       }
    }

    fun addMessageToHistoryFromBot(userEntity: UserEntity, message: String){
        try {
            val messageHistory = userEntity.messageHistory + "\nBOT(${LocalDateTime.now()}): \n $message"
            userEntity.messageHistory = messageHistory
            userRepository.save(userEntity);
        } catch (e: Exception){
            logger.error("Error while saving message history from bot", e.localizedMessage)
        }
    }

}

