package com.example.productme.feature_protect.domain.use_case

import android.content.SharedPreferences

class GetServiceStateUseCase(
    private val pref:SharedPreferences
) {
    operator fun invoke():Boolean{
       return pref.getBoolean("isServiceEnable",false)
    }
}