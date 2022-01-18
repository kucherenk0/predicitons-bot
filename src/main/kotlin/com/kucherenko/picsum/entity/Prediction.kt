package com.kucherenko.picsum.entity

import com.kucherenko.picsum.bot.PredictionType
import com.kucherenko.picsum.bot.UserState
import javax.persistence.*

@Entity
@Table(name = "prediction")
class Prediction(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "text", length = 2000)
    var text: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 200, nullable = true)
    var type: PredictionType,

    @Column(name = "deleted")
    var deleted: Boolean = false,
)