package com.sunasterisk.travelapp.data.models

import com.sunasterisk.travelapp.data.models.Type.ALL_TYPE
import com.sunasterisk.travelapp.data.models.Type.BREAKFAST_TYPE
import com.sunasterisk.travelapp.data.models.Type.BRUNCH_TYPE
import com.sunasterisk.travelapp.data.models.Type.DINNER_TYPE
import com.sunasterisk.travelapp.data.models.Type.LUNCH_TYPE

enum class MealType(val value: Int) {
    Breakfast(BREAKFAST_TYPE),
    Brunch(BRUNCH_TYPE),
    Lunch(LUNCH_TYPE),
    Dinner(DINNER_TYPE),
    All(ALL_TYPE)
}

object Type {
    const val BREAKFAST_TYPE = 10597
    const val BRUNCH_TYPE = 10606
    const val LUNCH_TYPE = 10598
    const val DINNER_TYPE = 10599
    const val ALL_TYPE = 0
}
