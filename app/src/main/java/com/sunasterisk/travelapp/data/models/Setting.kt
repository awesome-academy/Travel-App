package com.sunasterisk.travelapp.data.models

data class Setting(var language: String = ENGLISH, var currency: String = DOLAR) {
    companion object {
        const val ENGLISH = "en_US"
        const val VIETNAMESE = "vi_VN"
        const val DOLAR = "USD"
        const val VN_DONG = "VND"
    }
}
