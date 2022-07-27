package com.quinnpiling.quinn.rdf

import android.graphics.Color
import android.graphics.Paint

class RdfManager {
    companion object {

        val infoCategoryFont = RdfStyle(10F, RdfCustomColors.DarkBlue, underline = false, alignment = Paint.Align.LEFT)
        val infoCategoryFontCentered = RdfStyle(10F, RdfCustomColors.DarkBlue, underline = false, alignment = Paint.Align.CENTER)

        val infoFont = RdfStyle(10F, Color.BLACK, underline = false, alignment = Paint.Align.LEFT)
        val infoFontCentered = RdfStyle(10F, Color.BLACK, underline = false, alignment = Paint.Align.CENTER)

        val infoFontItalic = RdfStyle(10F, Color.BLACK, underline = false, alignment = Paint.Align.LEFT)
        val infoBoldFont = RdfStyle(10F, RdfCustomColors.Grey, underline = false, alignment = Paint.Align.LEFT)
        val segmentFont = RdfStyle(10F, RdfCustomColors.DarkBlue, underline = true, alignment = Paint.Align.LEFT)
        val pdfTitleFont = RdfStyle(16F, RdfCustomColors.DarkBlue, underline = false, alignment = Paint.Align.LEFT)

        val borderLessTableFormat = RdfStyle(1F, null, null, 1F, Paint.Align.LEFT)
        val borderedTableFormat = RdfStyle(1F, RdfCustomColors.LightGrey, null, 1F, Paint.Align.LEFT)

        val customDarkerFont = RdfStyle(6F, Color.BLACK, underline = false, alignment = Paint.Align.CENTER)
        val customDarkerFontSmaller = RdfStyle(5F, Color.BLACK, underline = false, alignment = Paint.Align.CENTER)
        val customDarkerFontTiny = RdfStyle(3F, Color.BLACK, underline = false, alignment = Paint.Align.CENTER)
        val customDarkFont = RdfStyle(4F, RdfCustomColors.Grey, underline = false, alignment = Paint.Align.CENTER)

    }

}