package com.kucherenko.picsum.entity

import com.kucherenko.picsum.bot.BaseColor
import com.kucherenko.picsum.bot.PredictionType
import com.kucherenko.picsum.bot.UserState
import javax.persistence.*

@Entity
@Table(name = "color")
class Color(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(name = "name", length = 200)
    var name: String?,

    @Column(name = "hex_code", length = 200)
    var hexCode: String?,

    @Column(name = "red_component")
    var redComponent: Int?,

    @Column(name = "green_component")
    var greenComponent: Int?,

    @Column(name = "blue_component")
    var blueComponent: Int?,

    @Enumerated(EnumType.STRING)
    @Column(name = "base_color", length = 200, nullable = true)
    var baseColor: BaseColor? = BaseColor.GREEN,

    @Column(name = "deleted")
    var deleted: Boolean = false,
)