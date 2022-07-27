package com.quinnpiling.quinn.rdf

import android.graphics.Paint

class RdfTable {
    val cellList = ArrayList<RdfCell>()
    var prioritizeCellStyle = false
    val columns: Int
    val columnWidths: ArrayList<Float>
    val rdfStyle: RdfStyle

    constructor(columns: Int, columnWidths: ArrayList<Float>, rdfStyle: RdfStyle) {
        if (columnWidths.find { it <= 0 } != null) throw IllegalArgumentException("Column width cannot be negative or zero.")
        this.columns = columns
        this.columnWidths = columnWidths
        this.rdfStyle = rdfStyle
        this.rdfStyle.style = Paint.Style.STROKE
    }

    constructor(columns: Int, tableWidth: Float, rdfStyle: RdfStyle) {
        val generalColumnWidth = tableWidth/ columns
        this.columns = columns
        columnWidths = ArrayList()
        for (i in 0 until columns) {
            columnWidths.add(generalColumnWidth)
        }
        this.rdfStyle = rdfStyle
        this.rdfStyle.style = Paint.Style.STROKE
    }

    fun getTotalTableWidth(): Float {
        return columnWidths.sum()
    }

    fun getBorderThickness(): Float {
        return rdfStyle.strokeWidth
    }

    fun getCellPadding(): Float {
        return rdfStyle.rdfPadding
    }

    fun addCell(cell: RdfCell) {
        cell.build(columnWidths[cellList.size % columns], rdfStyle.rdfPadding)
        cellList.add(cell)
    }
}