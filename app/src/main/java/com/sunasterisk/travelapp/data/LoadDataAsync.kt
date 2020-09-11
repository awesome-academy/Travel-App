package com.sunasterisk.travelapp.data

import android.os.AsyncTask

class LoadDataAsync<T, P>(
    private val callbackListener: OnDataCallback<P>,
    private val handler: (T) -> Any
) : AsyncTask<T, Unit, Any>() {
    override fun doInBackground(vararg params: T): Any =
        try {
            handler(params[0])
        } catch (exception: Exception) {
            exception
        }

    override fun onPostExecute(result: Any) {
        super.onPostExecute(result)
        try {
            if (result is Throwable) {
                callbackListener.onError(result)
            } else {
                callbackListener.onSuccess(result as P)
            }
        } catch (exception: ClassCastException) {
            callbackListener.onError(Exception(EXCEPTION))
        }
    }

    companion object {
        const val EXCEPTION = "Loading data failed, exception occurred"
    }
}
