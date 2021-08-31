package com.ozzy.bunqer.di

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.ozzy.bunqer.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Oğuzhan Karacan on 31.08.2021.
 */
@Singleton
class BunqPreferences @Inject constructor(@ApplicationContext context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(Constants.Preferences.PREF_NAME, Context.MODE_PRIVATE)

    fun getString(key: String): String = prefs.getString(key, "")!!

    fun putString(key: String, value: String) {
        prefs.edit { putString(key, value) }
    }

}