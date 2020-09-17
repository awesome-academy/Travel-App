package com.sunasterisk.travelapp.ui.base

import androidx.annotation.StringRes

interface BaseContract {

    interface View<T> {

        fun showProgressDialog()

        fun dismissProgressDialog()

        fun showToastMessage(message: String?)

        fun showToastMessage(@StringRes stringResourceId: Int)

        fun showSnackBarMessage(message: String?)

        fun showSnackBarMessage(@StringRes stringResourceId: Int)

        fun onError(message: String?)

        fun onError(@StringRes resId: Int)

    }

    interface Presenter<V> {

        fun onAttach(view: V)

        fun onDetach()

        fun handleApiError(throwable: Throwable?)
    }
}
