package com.quinnpiling.quinn.rdf

import android.graphics.Paint

class RdfText(val text: String, val maxWidth: Float, val rdfStyle: RdfStyle) {
    val rdfTextLines = ArrayList<RdfTextLine>()
    val totalHeight: Float

    init {
        if (maxWidth < 0) throw IllegalArgumentException("Cannot have negative width space for RdfText")
        buildTextLines()
        totalHeight = (rdfStyle.rdfLineSpacing + rdfStyle.textSize) * rdfTextLines.count()
    }


    fun getLineSpacing():Float{
        return rdfStyle.rdfLineSpacing
    }

    fun getTextSize():Float{
        return rdfStyle.textSize
    }

    fun getTextAlign():Paint.Align{
        return rdfStyle.textAlign
    }

    private fun buildTextLines() {
        var temp = text
        while (true) {
            if (temp.isBlank()) break
            val highestIndex = temp.length - 1
            val nextStartingIndex = rdfStyle.breakText(temp, true, maxWidth, null)
            if (nextStartingIndex == 0) break
            val textLine = temp.substring(0, nextStartingIndex)
            val breakIndex = textLine.indexOf('\n')
            if (breakIndex != -1) {
                val breakText = temp.substring(0, breakIndex)
                rdfTextLines.add(RdfTextLine(breakText, rdfStyle))
                if (breakIndex < highestIndex) {
                    temp = temp.substring(breakIndex + 1)
                    continue
                }
                break
            }
            if (nextStartingIndex > highestIndex) {
                rdfTextLines.add(RdfTextLine(temp, rdfStyle))
                break
            }
            if (temp[nextStartingIndex] == ' ') {
                rdfTextLines.add(RdfTextLine(textLine, rdfStyle))
                if (nextStartingIndex < highestIndex)
                    temp = temp.substring(nextStartingIndex + 1)
                else break
            } else {
                val lastSpaceIndex = textLine.lastIndexOf(' ')
                temp = if (lastSpaceIndex != -1) {
                    val spaceText = textLine.substring(0, lastSpaceIndex)
                    rdfTextLines.add(RdfTextLine(spaceText, rdfStyle))
                    temp.substring(lastSpaceIndex + 1)
                } else {
                    rdfTextLines.add(RdfTextLine(textLine, rdfStyle))
                    temp.substring(nextStartingIndex)
                }
            }
        }
    }
}