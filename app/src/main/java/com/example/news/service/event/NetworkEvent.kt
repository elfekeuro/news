package com.example.weatherforecast.service.event


sealed class NetworkEvent {

    data class Loading(val isShow: Boolean) : NetworkEvent()

    data class Success(val data: Any?) : NetworkEvent()

    data class Failure(val message: String) : NetworkEvent()
}