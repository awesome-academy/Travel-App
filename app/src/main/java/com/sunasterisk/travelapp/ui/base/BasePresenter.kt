package com.sunasterisk.travelapp.ui.base

import com.sunasterisk.travelapp.R

abstract class BasePresenter<V : BaseContract.View<*>>: BaseContract.Presenter<V> {

    private var view: V? = null

    protected val isViewAttached: Boolean
        get() = view != null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        // Unregister callback
        view = null
    }

    override fun handleApiError(throwable: Throwable?) {
        if (throwable == null) {
            view?.onError(R.string.error_default_message)
        } else {
            view?.onError(throwable.message)
        }
    }
}
