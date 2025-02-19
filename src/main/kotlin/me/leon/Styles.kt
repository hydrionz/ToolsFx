package me.leon

import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        // Define our styles
        val myButton by cssclass()

        // Define our colors
        val hoverColor = c("#a1a3a6")
        val dangerColor = c("#a94442")

        // Define our size
        val px14 = 14.px
        val px5 = 5.px
        val px10 = 10.px
        val px20 = 20.px
    }

    init {

        myButton {
            padding = box(px10)
            fontSize = px20
            fontWeight = FontWeight.BOLD
        }
        button {
            fontSize = px14
            fontWeight = FontWeight.BOLD
            //            padding = box(10.px)
            and(hover) {
                backgroundColor += hoverColor
                //             borderColor += box(dangerColor)
            }
        }

        label {
            fontSize = px14
            padding = box(px5, px10)
            fontWeight = FontWeight.BOLD
            maxWidth = infinity
            //            borderColor += box(dangerColor)
            //            borderStyle += BorderStrokeStyle(
            //                StrokeType.INSIDE,
            //                StrokeLineJoin.MITER,
            //                StrokeLineCap.BUTT,
            //                10.0,
            //                0.0,
            //                listOf(25.0, 5.0)
            //            )
            //            borderWidth += box(5.px)
            //
            //            and(hover) {
            //                backgroundColor += hoverColor
            //                borderColor += box(dangerColor)
            //            }

        }
    }
}
