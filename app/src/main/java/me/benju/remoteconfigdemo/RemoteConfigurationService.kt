package me.benju.remoteconfigdemo

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.concurrent.TimeUnit

object RemoteConfigurationService {

    private val firebaseRemoteConfiguration = FirebaseRemoteConfig.getInstance()

    val getHolidayPromoTitle
        get() = firebaseRemoteConfiguration.getString("holiday_promo_title")

    val getHolidayPromoButton
        get() = firebaseRemoteConfiguration.getString("holiday_promo_button")

    val getHolidayPromoEnabled
        get() = firebaseRemoteConfiguration.getBoolean("holiday_promo_enabled")

    //getDouble

    init {
        var cacheInterval = TimeUnit.HOURS.toSeconds(2L)
        if (BuildConfig.DEBUG) cacheInterval = 0
        val firebaseRemoteConfigurationSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(cacheInterval)
            .build()
        firebaseRemoteConfiguration.setConfigSettingsAsync(firebaseRemoteConfigurationSettings)
        firebaseRemoteConfiguration.setDefaultsAsync(R.xml.remote_config_defaults)

        firebaseRemoteConfiguration.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d("RemoteConfig", "Config params updated: $updated")
                    Log.d("RemoteConfig", "Fetching Firebase remote configuration succeeded")
                } else {
                    Log.e("RemoteConfig", "Fetching Firebase remote configuration failed")
                }
            }
    }

}