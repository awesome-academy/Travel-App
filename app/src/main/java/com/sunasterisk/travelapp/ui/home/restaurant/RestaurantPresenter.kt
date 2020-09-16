package com.sunasterisk.travelapp.ui.home.restaurant

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.repository.LocationRepository
import com.sunasterisk.travelapp.ui.base.BasePresenter

class RestaurantPresenter(
    private val locationRepository: LocationRepository,
) : BasePresenter<RestaurantContract.View>(), RestaurantContract.Presenter {

    override fun searchLocations(input: String) {
        locationRepository.searchLocationsByProperty(
            input,
            object : OnDataCallback<List<Location>> {
                override fun onSuccess(data: List<Location>) {
                    view?.updateLocations(data)
                    view?.showProgressDialog()
                }

                override fun onError(throwable: Throwable) {
                    view?.onError(throwable.message)
                }
            })
    }
}
