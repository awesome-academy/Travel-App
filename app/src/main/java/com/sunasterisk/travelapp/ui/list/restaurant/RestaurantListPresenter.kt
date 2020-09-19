package com.sunasterisk.travelapp.ui.list.restaurant

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.data.repository.RestaurantRepository
import com.sunasterisk.travelapp.ui.base.BasePresenter

class RestaurantListPresenter(
    private val restaurantRepo: RestaurantRepository
) : BasePresenter<RestaurantListContract.View>(), RestaurantListContract.Presenter {

    override fun getRestaurants(
        latitude: String,
        longtitude: String,
        mealType: String?,
        restaurantType: String?
    ) {
        view?.showProgressDialog()
        restaurantRepo.searchRestaurantsByProperty(
            latitude,
            longtitude,
            mealType,
            restaurantType,
            object : OnDataCallback<List<Restaurant>> {
                override fun onSuccess(data: List<Restaurant>) {
                    view?.updateRestaurants(data)
                }

                override fun onError(throwable: Throwable) {
                    view?.onError(throwable.message)
                    view?.dismissProgressDialog()
                }
            })
    }
}
