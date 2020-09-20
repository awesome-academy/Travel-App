package com.sunasterisk.travelapp.ui.home.hotel

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.repository.LocationRepository
import com.sunasterisk.travelapp.ui.base.BasePresenter

class HotelPresenter(
    private val locationRepository: LocationRepository,
) : BasePresenter<HotelContract.View>(), HotelContract.Presenter {

    override fun searchLocations(input: String) {
        view?.showProgressDialog()
        locationRepository.searchLocationsByProperty(
            input,
            object : OnDataCallback<List<Location>> {
                override fun onSuccess(data: List<Location>) {
                    view?.updateLocations(data)
                }

                override fun onError(throwable: Throwable) {
                    view?.onError(throwable.message)
                }
            })
    }
}
