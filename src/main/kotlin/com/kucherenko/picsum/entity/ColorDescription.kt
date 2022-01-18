package com.kucherenko.picsum.entity

import com.kucherenko.picsum.bot.BaseColor
import com.kucherenko.picsum.bot.PredictionType
import com.kucherenko.picsum.bot.UserState
import javax.persistence.*

@Entity
@Table(name = "color_description")
class ColorDescription(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "base_color", length = 200, nullable = true)
    var baseColor: BaseColor,

    @Column(name = "text", length = 2000)
    var text: String,

    @Column(name = "deleted")
    var deleted: Boolean = false,
)