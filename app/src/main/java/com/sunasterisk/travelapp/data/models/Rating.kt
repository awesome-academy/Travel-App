package com.sunasterisk.travelapp.data.models

import org.json.JSONObject

data class Rating(
    var oneStar: Int = 0,
    var twoStar: Int = 0,
    var threeStar: Int = 0,
    var fourStar: Int = 0,
    var fiveStar: Int = 0
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(ONE_STAR).toInt(),
        jsonObject.getString(TWO_STAR).toInt(),
        jsonObject.getString(THREE_STAR).toInt(),
        jsonObject.getString(FOUR_STAR).toInt(),
        jsonObject.getString(FIVE_STAR).toInt()
    )

    companion object {
        private const val ONE_STAR = "count_1"
        private const val TWO_STAR = "count_2"
        private const val THREE_STAR = "count_3"
        private const val FOUR_STAR = "count_4"
        private const val FIVE_STAR = "count_5"
    }
}
