package com.example.myapplication.model

data class Cell(
    var value: Int? = null,
    var row: Int? = null,
    var col: Int? = null
) {
    override fun toString(): String {
        return "$row.$col"
    }
}

