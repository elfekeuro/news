package com.example.news.base

import com.google.gson.Gson
import retrofit2.Call
import java.net.HttpURLConnection

abstract class BaseRepository {

    protected fun <T> responseAdapter(call: Call<T>): T? {
        return try {
            val response = call.execute()
            when {
                response.isSuccessful -> {
                    val data = response.body() as BaseResponse<Any>
                    data.code = response.code().toString()
                    data as T
                }
                else -> {
                    val baseResponse =
                        Gson().fromJson(
                            response.errorBody()?.string(),
                            BaseResponse::class.java
                        )
                    baseResponse.code = response.code().toString()
                    baseResponse as T
                }
            }
        } catch (exception: Throwable) {
            BaseResponse<T>().apply {
                code = HttpURLConnection.HTTP_BAD_REQUEST.toString()
                message = exception.message
            } as T
        } catch (exception: ClassCastException) {
            BaseResponse<T>().apply {
                code = HttpURLConnection.HTTP_BAD_REQUEST.toString()
                message = exception.message
            } as T
        }
    }

}
