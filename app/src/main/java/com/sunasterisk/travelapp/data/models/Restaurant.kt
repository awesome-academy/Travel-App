package com.sunasterisk.travelapp.data.models

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

data class Restaurant(
    val id: String,
    val name: String = "",
    val location: String = "",
    val address: String = "",
    val email: String = "",
    val phone: String = "",
    val price: String = "",
    var imageThumbRestaurant: String = "",
    var imageLargeRestaurant: String = "",
    var ratingCount: Double = 0.0,
    var rating: Rating = Rating(),
    var descriptionRestaurant: String = "",
    var reviews: MutableList<Review> = mutableListOf()
) {

    @Throws(JSONException::class)
    constructor(restaurantObject: JSONObject) : this(
        restaurantObject.getString(LOCATION_ID),
        restaurantObject.getString(NAME),
        restaurantObject.getString(LOCATION_STRING),
        restaurantObject.getString(ADDRESS),
        restaurantObject.getString(EMAIL),
        restaurantObject.getString(PHONE),
        restaurantObject.getString(PRICE),
        restaurantObject
            .getJSONObject(PHOTO)
            .getJSONObject(IMAGES)
            .getJSONObject(THUMB_IMAGE)
            .getString(URL),
        restaurantObject
            .getJSONObject(PHOTO)
            .getJSONObject(IMAGES)
            .getJSONObject(LARGE_IMAGE)
            .getString(URL),
        restaurantObject.getString(RATING_COUNT).toDouble(),
        Rating(restaurantObject.getJSONObject(RATING)),
        restaurantObject.getString(DESCRIPTION),
        Review.jsonToArray(restaurantObject.getJSONArray(REVIEWS))
    )

    companion object {
        private const val LOCATION_ID = "location_id"
        private const val NAME = "name"
        private const val LOCATION_STRING = "location_string"
        private const val ADDRESS = "address"
        private const val PHOTO = "photo"
        private const val IMAGES = "images"
        private const val LARGE_IMAGE = "large"
        private const val THUMB_IMAGE = "medium"
        private const val URL = "url"
        private const val PRICE = "price"
        private const val DESCRIPTION = "description"
        const val EMAIL = "email"
        const val PHONE = "phone"
        const val RATING_COUNT = "rating"
        const val RATING = "rating_histogram"
        const val REVIEWS = "reviews"

        fun jsonToArray(jsonArray: JSONArray): MutableList<Restaurant> {
            val mutableList = mutableListOf<Restaurant>()
            for (index in 0 until jsonArray.length()) {
                try {
                    val restaurantJSONObject = jsonArray.getJSONObject(index)
                    val thumbJSONObject = jsonArray.getJSONObject(index)
                        .getJSONObject(PHOTO)
                        .getJSONObject(IMAGES)
                        .getJSONObject(THUMB_IMAGE)
                    mutableList.add(
                        Restaurant(
                            id = restaurantJSONObject.getString(LOCATION_ID),
                            name = restaurantJSONObject.getString(NAME),
                            imageThumbRestaurant = thumbJSONObject.getString(URL),
                            location = restaurantJSONObject.getString(LOCATION_STRING),
                            ratingCount = restaurantJSONObject.getString(RATING_COUNT).toDouble()
                        )
                    )
                } catch (jsonException: JSONException) {
                    jsonException.printStackTrace()
                }
            }
            return mutableList
        }
    }
}
