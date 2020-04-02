package com.adyabukhari.tunaikuasessment.utils.helper

object NumericRegex {
    fun isNumeric(text: String): Boolean {
        return Regex("^[0-9]*$").matches(text.trim())
    }
}