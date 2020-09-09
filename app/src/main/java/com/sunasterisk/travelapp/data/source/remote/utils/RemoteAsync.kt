package com.sunasterisk.travelapp.data.source.remote.utils

import android.os.AsyncTask
import com.sunasterisk.travelapp.data.OnDataCallback

class RemoteAsync(private val callbackListener: OnDataCallback<String>) :
    AsyncTask<String, Unit, Any>() {
    override fun doInBackground(vararg params: String?): Any =
        HttpUtils.getApi(params[0].toString())

    override fun onPostExecute(result: Any?) {
        super.onPostExecute(result)
        result?.let {
            if (it is Throwable) {
                callbackListener.onError(it)
            } else {
                callbackListener.onSuccess(it.toString())
            }
        } ?: callbackListener.onError(Exception(EXCEPTION))
    }

    companion object {
        private const val EXCEPTION = "Result is empty"
    }
}
