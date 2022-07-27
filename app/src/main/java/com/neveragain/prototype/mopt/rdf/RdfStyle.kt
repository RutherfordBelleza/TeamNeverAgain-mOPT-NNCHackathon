package com.quinnpiling.quinn.rdf

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface

class RdfStyle : Paint {

    var rdfLineSpacing = 5F
    var rdfBGColor: Int? = null
    var rdfPadding: Float
    var rdfTextColor: Int
    var rdfLineColor: Int?
    var rdfTableAlignment: Align

    constructor() : super() {
        rdfTextColor = Color.BLACK
        rdfLineColor = Color.BLACK
        rdfPadding = 1F
        rdfTableAlignment = Align.LEFT
        typeface = Typeface.SANS_SERIF
    }

    constructor(textSize: Float, textColor: Int, underline: Boolean, alignment: Align):super(){
        this.textSize = textSize
        rdfTextColor = textColor
        rdfLineColor = Color.BLACK
        isUnderlineText = underline
        this.textAlign = alignment
        rdfPadding = 1F
        rdfTableAlignment = Align.LEFT
    }

    constructor(textSize: Float, textColor: Int, italicize: Boolean, bold: Boolean, underline: Boolean, alignment: Align) : super() {
        this.textSize = textSize
        rdfTextColor = textColor
        rdfLineColor = Color.BLACK
        if (italicize) textSkewX = -0.25f
        isFakeBoldText = bold
        isUnderlineText = underline
        this.textAlign = alignment
        rdfPadding = 1F
        rdfTableAlignment = Align.LEFT
    }

    constructor(borderThickness: Float, borderColor: Int?, bgColor: Int?, padding: Float, tableAlign: Align) : super() {
        this.rdfTextColor = Color.BLACK
        this.rdfLineColor = borderColor
        this.rdfBGColor = bgColor
        this.strokeWidth = borderThickness
        this.rdfPadding = padding
        this.rdfTableAlignment = tableAlign
    }

    fun useBgColor() {
        if (rdfBGColor != null) color = rdfBGColor!!
        style = Style.FILL
    }

    fun useTextColor() {
        color = rdfTextColor
        style = Style.FILL
    }

    fun useLineColor() {
        if (rdfLineColor != null) color = rdfLineColor!!
        style = Style.STROKE
    }

    fun resetColor() {
        color = Color.BLACK
        style = Style.FILL
    }
}