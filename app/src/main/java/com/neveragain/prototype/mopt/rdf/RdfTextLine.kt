package com.quinnpiling.quinn.rdf

class RdfTextLine(val text: String, rdfStyle: RdfStyle) {
    val width: Float = rdfStyle.measureText(text)
}