package com.sunasterisk.travelapp.data.source.remote.utils

import android.accounts.NetworkErrorException
import com.sunasterisk.travelapp.BuildConfig
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object HttpUtils {
    private const val METHOD_GET = "GET"
    private const val CONNECT_TIMEOUT = 15000
    private const val READ_TIMEOUT = 10000
    private const val KEY_API = BuildConfig.API_KEY
    private const val RAPIDAPI_KEY = "x-rapidapi-key"

    fun getApi(
        urlString: String
    ): Any {
        var result: Any
        var httpURLConnection: HttpURLConnection? = null
        try {
            httpURLConnection = (URL(urlString).openConnection() as HttpURLConnection).apply {
                requestMethod = METHOD_GET
                connectTimeout = CONNECT_TIMEOUT
                readTimeout = READ_TIMEOUT
                setRequestProperty(RAPIDAPI_KEY, KEY_API)
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val bufferedReader =
                        BufferedReader(InputStreamReader(inputStream))
                    val buffer = StringBuffer()
                   bufferedReader.forEachLine {
                       buffer.append(it)
                   }
                    bufferedReader.close()
                    inputStream.close()
                    result = buffer.toString()
                } else {
                    result = NetworkErrorException("$responseCode")
                }
            }
        } catch (e: MalformedURLException) {
            result = e
        } catch (e: IOException) {
            result = e
        } finally {
            httpURLConnection?.disconnect()
        }
        return result
    }
}
