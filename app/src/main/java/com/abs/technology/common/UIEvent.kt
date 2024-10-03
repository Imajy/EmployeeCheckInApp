package com.abs.technology.common

sealed class UIEvent{
    object popBackStack: UIEvent()
    data class Navigate(val route:String): UIEvent()
    data class ShowSnackBar(
        val message:String,
        val action:String?=null
    ): UIEvent()
}
