package com.abs.technology.common

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel@Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel(){
    fun clearSharedModel() {
        sharedPreferences.edit().clear().apply()
    }

    fun setupdateAvatar(update:Boolean){
        sharedPreferences.edit().putBoolean("avatarUpdate",update).apply()
    }
    fun getupdateAvatar():Boolean{
        return sharedPreferences.getBoolean("avatarUpdate",false)
    }
}