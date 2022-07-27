package com.quinnpiling.quinn.rdf

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import com.quinnpiling.quinn.rdf.RdfConstants.Companion.PAGE_HEIGHT_DEFAULT
import com.quinnpiling.quinn.rdf.RdfConstants.Companion.PAGE_WIDTH_DEFAULT
import java.io.FileOutputStream

class RdfDocument(private val outputStream: FileOutputStream) : PdfDocument() {
    private var currentPageNumber = 0
    private var documentPageWidth: Int = PAGE_WIDTH_DEFAULT
    private var documentPageHeight: Int = PAGE_HEIGHT_DEFAULT
    private var documentMarginTop = 36F
    private var documentMarginBottom = 36F
    private var documentMarginLeft = 36F
    private var documentMarginRight = 36F
    private var yCursor = 0F
    var currentPage: Page
        private set

    init {
        val pageInfo = PageInfo.Builder(documentPageWidth, documentPageHeight, currentPageNumber).create()
        currentPage = startPage(pageInfo)
    }

    constructor(outputStream: FileOutputStream, pageWidth: Int, pageHeight: Int) : this(outputStream) {
        documentPageWidth = pageWidth
        documentPageHeight = pageHeight
    }

    constructor(outputStream: FileOutputStream, pageWidth: Int, pageHeight: Int, marginTop: Float, marginRight: Float, marginBottom: Float, marginLeft: Float) : this(outputStream, pageWidth, pageHeight) {
        documentMarginTop = marginTop
        documentMarginRight = marginRight
        documentMarginBottom = marginBottom
        documentMarginLeft = marginLeft
    }

    constructor(outputStream: FileOutputStream, pageWidth: Int, pageHeight: Int, margin: Float) : this(outputStream, pageWidth, pageHeight, margin, margin, margin, margin)

    private fun manualAddText(rdfText: RdfText, xInitial: Float, xFinal: Float, yInitial: Float) {
        for ((i, textLine) in rdfText.rdfTextLines.withIndex()) {
            val yPosition = yInitial + (rdfText.getLineSpacing() + rdfText.getTextSize()) * i
            val xPosition = when (rdfText.getTextAlign()) {
                Paint.Align.CENTER -> (xInitial + xFinal - textLine.width) / 2
                Paint.Align.RIGHT -> xFinal - textLine.width
                else -> xInitial
            }
            val paint = Paint(rdfText.rdfStyle)
            paint.textAlign = Paint.Align.LEFT
            currentPage.canvas?.drawText(textLine.text, xPosition, yPosition + rdfText.getTextSize(), paint)
        }
    }

    private fun addText(rdfText: RdfText, xInitial: Float, xFinal: Float, yInitial: Float, paddingTop: Float, paddingRight: Float, paddingBottom: Float, paddingLeft: Float) {
        var i = 0
        var yInitialTemp = yInitial
        for (textLine in rdfText.rdfTextLines) {
            var yPosition = yInitialTemp + paddingTop + (rdfText.getLineSpacing() + rdfText.getTextSize()) * i
            val xPosition = when (rdfText.getTextAlign()) {
                Paint.Align.CENTER -> (xInitial + xFinal - textLine.width) / 2
                Paint.Align.RIGHT -> xFinal - paddingRight - textLine.width
                else -> xInitial + paddingLeft
            }
            if (yPosition + rdfText.getTextSize() + rdfText.getLineSpacing() > documentPageHeight - documentMarginBottom) {
                createNewPage()
                i = 0
                yInitialTemp = documentMarginTop
                yPosition = yInitialTemp + paddingTop + (rdfText.getLineSpacing() + rdfText.getTextSize()) * i
            }
            val paint = Paint(rdfText.rdfStyle)
            paint.textAlign = Paint.Align.LEFT
            currentPage.canvas?.drawText(textLine.text, xPosition, yPosition + rdfText.getTextSize(), paint)
            i++
        }
        yCursor += (rdfText.getLineSpacing() + rdfText.getTextSize()) * i
    }

    fun addParagraph(text: String, rdfStyle: RdfStyle, paddingTop: Float, paddingRight: Float, paddingBottom: Float, paddingLeft: Float) {
        val startPoint = documentMarginLeft
        val endPoint = documentPageWidth - documentMarginRight
        val maxLineLength = endPoint - startPoint
        val rdfText = RdfText(text, maxLineLength, rdfStyle)
        addText(rdfText, startPoint, endPoint, documentMarginTop + yCursor, paddingTop, paddingRight, paddingBottom, paddingLeft)
    }

    fun addParagraph(text: String, rdfStyle: RdfStyle, padding: Float) {
        rdfStyle.useTextColor()
        addParagraph(text, rdfStyle, padding, padding, padding, padding)
    }

    fun addParagraph(text: String) {
        addParagraph(text, RdfManager.infoFont, 0F)
    }

    private fun getStartPoint(): Float {
        return documentMarginLeft
    }

    private fun getEndPoint(): Float {
        return documentPageWidth - documentMarginRight
    }

    fun getWidthWithinMargin(): Float {
        return getEndPoint() - getStartPoint()
    }

    fun addTable(rdfTable: RdfTable) {
        if (rdfTable.getTotalTableWidth() > getWidthWithinMargin()) throw IllegalArgumentException("Table too large for document.")
        val rows = rdfTable.cellList.chunked(rdfTable.columns)
        var yOffset = yCursor + documentMarginTop
        for (row in rows) {
            val greatestHeightObject = row.maxByOrNull { it.height }
            val greatestHeight = greatestHeightObject!!.height
            if (yOffset + greatestHeight > documentPageHeight - documentMarginBottom) {
                createNewPage()
                yOffset = documentMarginTop
            }
            var xOffset = when (rdfTable.rdfStyle.rdfTableAlignment) {
                Paint.Align.CENTER -> (getStartPoint() + getEndPoint() - rdfTable.getTotalTableWidth()) / 2
                Paint.Align.RIGHT -> getEndPoint() - rdfTable.getTotalTableWidth()
                else -> getStartPoint()
            }
            for (cell in row) {
                if (rdfTable.prioritizeCellStyle) {
                    if (cell.rdfStyle.rdfBGColor != null) {
                        cell.rdfStyle.useBgColor()
                        currentPage.canvas.drawRect(xOffset, yOffset, xOffset + cell.width, yOffset + greatestHeight, cell.rdfStyle)
                    }
                    if (cell.rdfStyle.rdfLineColor != null) {
                        cell.rdfStyle.useLineColor()
                        currentPage.canvas.drawRect(xOffset, yOffset, xOffset + cell.width, yOffset + greatestHeight, cell.rdfStyle)
                    }
                    cell.rdfStyle.useTextColor()
                    manualAddText(cell.rdfText, xOffset + cell.padding, xOffset + cell.width - cell.padding, yOffset + cell.padding)
                    xOffset += cell.width
                } else {
                    if (rdfTable.rdfStyle.rdfBGColor != null) {
                        rdfTable.rdfStyle.useBgColor()
                        currentPage.canvas.drawRect(xOffset, yOffset, xOffset + cell.width, yOffset + greatestHeight, rdfTable.rdfStyle)
                    }
                    if (rdfTable.rdfStyle.rdfLineColor != null) {
                        rdfTable.rdfStyle.useLineColor()
                        currentPage.canvas.drawRect(xOffset, yOffset, xOffset + cell.width, yOffset + greatestHeight, rdfTable.rdfStyle)
                    }
                    cell.rdfStyle.useTextColor()
                    manualAddText(cell.rdfText, xOffset + cell.padding, xOffset + cell.width - cell.padding, yOffset + cell.padding)
                    xOffset += cell.width
                }
            }
            yOffset += greatestHeight
        }
        yCursor = yOffset - documentMarginTop
    }

    override fun close() {
        finishPage(currentPage)
        writeTo(outputStream)
        outputStream.close()
        super.close()
    }

    fun createNewPage() {
        this.finishPage(currentPage)
        yCursor = 0F
        currentPageNumber++
        val pageInfo = PageInfo.Builder(documentPageWidth, documentPageHeight, currentPageNumber).create()
        currentPage = this.startPage(pageInfo)
    }

    private fun addBlankSpace(height: Float) {
        if (documentMarginTop + yCursor + height > documentPageHeight - documentMarginBottom) createNewPage()
        else yCursor += height
    }

    fun addLargeBlankSpace() {
        addBlankSpace(20F)
    }

    fun addTinyBlankSpace() {
        addBlankSpace(3F)
    }

    fun addRegularBlankSpace() {
        addBlankSpace(10F)
    }

    fun drawImage(bitmap: Bitmap, xInitial: Float, yInitial: Float) {
        currentPage.canvas.drawBitmap(bitmap, xInitial, yInitial, Paint())
    }

    fun drawImage(bitmap: Bitmap) {
        if (yCursor + documentMarginTop + bitmap.height > documentPageHeight - documentMarginBottom) createNewPage()
        drawImage(bitmap, documentMarginLeft, yCursor + documentMarginTop)
        yCursor += bitmap.height
    }

}