package com.sunasterisk.travelapp.data

import android.os.AsyncTask

class LoadDataAsync<T, P>(
    private val callbackListener: OnDataCallback<P>,
    private val handler: (T) -> P?
) : AsyncTask<T, Unit, P?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg params: T): P? =
        try {
            handler(params[0])
        } catch (exception: Exception) {
            this.exception = exception
            null
        }

    override fun onPostExecute(result: P?) {
        super.onPostExecute(result)
        result?.let {
            callbackListener.onSuccess(it)
        } ?: callbackListener.onError(exception ?: Exception(EXCEPTION))
    }

    companion object {
        const val EXCEPTION = "Loading data failed, exception occurred"
    }
}
