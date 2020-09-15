package com.sunasterisk.travelapp.data.models

import com.sunasterisk.travelapp.data.models.RestaurentType.ALL
import com.sunasterisk.travelapp.data.models.RestaurentType.BAKERIES
import com.sunasterisk.travelapp.data.models.RestaurentType.COFFEE
import com.sunasterisk.travelapp.data.models.RestaurentType.DESSERT
import com.sunasterisk.travelapp.data.models.RestaurentType.QUICKBITES
import com.sunasterisk.travelapp.data.models.RestaurentType.RESTAURANTS

enum class RestaurantType(val value: String) {
    Restaurants(RESTAURANTS),
    QuickBites(QUICKBITES),
    Dessert(DESSERT),
    Coffee(COFFEE),
    Bakeries(BAKERIES),
    All(ALL)
}

object RestaurentType {
    const val RESTAURANTS = "10591"
    const val QUICKBITES = "16556"
    const val DESSERT = "9909"
    const val COFFEE = "9900"
    const val BAKERIES = "9901"
    const val ALL = "-1"
}
