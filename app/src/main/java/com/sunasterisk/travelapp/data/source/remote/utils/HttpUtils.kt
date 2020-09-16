package com.sunasterisk.travelapp.data.source.remote.utils

import com.sunasterisk.travelapp.BuildConfig
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.jvm.Throws

object HttpUtils {
    private const val METHOD_GET = "GET"
    private const val CONNECT_TIMEOUT = 15000
    private const val READ_TIMEOUT = 10000
    private const val KEY_API = BuildConfig.API_KEY
    private const val RAPIDAPI_KEY = "x-rapidapi-key"

    @Throws(IOException::class)
    fun getApi(
        urlString: String
    ): String? {
        var result: String? = null
        var httpURLConnection: HttpURLConnection? = null
        httpURLConnection = (URL(urlString).openConnection() as HttpURLConnection).apply {
            requestMethod = METHOD_GET
            connectTimeout = CONNECT_TIMEOUT
            readTimeout = READ_TIMEOUT
            setRequestProperty(RAPIDAPI_KEY, KEY_API)
            result = if (responseCode == HttpURLConnection.HTTP_OK) {
                val bufferedReader =
                    BufferedReader(InputStreamReader(inputStream))
                val buffer = StringBuffer()
                bufferedReader.forEachLine {
                    buffer.append(it)
                }
                bufferedReader.close()
                inputStream.close()
                buffer.toString()
            } else {
                null
            }
        }
        httpURLConnection?.disconnect()
        return result
    }
}
