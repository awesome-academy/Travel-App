package com.sunasterisk.travelapp.data

interface OnDataCallback<T> {

    fun onSuccess(data: T)

    fun onError(throwable: Throwable)
}
