package com.sunasterisk.travelapp.utils

import java.text.Normalizer
import java.util.regex.Pattern

fun String.convertToPrice(): String {
    val prices = this.split("-")
    if (prices.size > 1) {
        val firstPrice = prices[0]
            .split(".", ",")[0]
            .filter { it in '0'..'9' }
        var secondPrice = prices[1]
            .split(".", ",")[0]
            .filter { it in '0'..'9' }
        if (secondPrice == "1") secondPrice += prices[1].split(".", ",")[1]
        return "${firstPrice}$ - ${secondPrice}$"
    }
    return "60$ - 80$"
}

fun String.getPrice(): Double {
    val prices = this.split("-")
    if (prices.size > 1) {
        val firstPrice = prices[0]
            .split(".", ",")[0]
            .filter { it in '0'..'9' }
        val secondPrice = prices[1]
            .split(".", ",")[0]
            .filter { it in '0'..'9' }
        val result = firstPrice.toDouble() + secondPrice.toDouble()
        return result / 2.0
    }
    return 0.0
}

fun String.removeAccent(): String {
    val tmp = Normalizer.normalize(this, Normalizer.Form.NFD)
    val pattern =
        Pattern.compile("\\p{InCOMBINING_DIACRITICAL_MARKS}+")
    return pattern.matcher(tmp).replaceAll("")
}
