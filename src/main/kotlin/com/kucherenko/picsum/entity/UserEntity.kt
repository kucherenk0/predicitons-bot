package com.kucherenko.picsum.entity

import com.kucherenko.picsum.bot.UserState
import javax.persistence.*

@Entity
//TODO: constrains
@Table(name = "user_entity")
class UserEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "tg_id", length = 200)
    var telegramId: String,

    @Column(name = "tg_name", length = 1000)
    var telegramName: String,

    @Column(name = "chat_id", length = 200)
    var chatId: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 200, nullable = true)
    var userState: UserState,

    @Column(name = "deleted")
    var deleted: Boolean = false,

    @Column(name = "message_history", length = 1000000)
    var messageHistory: String = ""
)