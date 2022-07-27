package com.quinnpiling.quinn.rdf

class RdfCell(val text: String, val rdfStyle: RdfStyle) {
    lateinit var rdfText: RdfText
    var width = 0F
    var height = 0F
    var padding = 0F
    fun build(width: Float, padding: Float) {
        this.width = width
        this.padding = padding
        this.rdfText = RdfText(text, width - padding * 2, rdfStyle)
        this.height = rdfText.totalHeight + padding * 2
    }
}
