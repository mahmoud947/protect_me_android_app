package com.example.productme.feature_protect.domain.use_case

import android.content.SharedPreferences

class SaveServiceStateUseCase(
    private val pref:SharedPreferences
) {
    operator fun invoke(isServiceEnable:Boolean){
        pref.edit()
            .putBoolean("isServiceEnable",isServiceEnable)
            .apply()
    }
}