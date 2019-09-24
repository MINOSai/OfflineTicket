package com.minosai.offlineticket

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.minosai.common.Constants
import com.minosai.common.toast
import com.minosai.feature_auth.AuthActivity
import com.minosai.feature_conductor.ConductorActivity
import com.minosai.feature_passenger.PassengerActivity
import org.koin.android.ext.android.inject
import com.minosai.local.util.PreferenceHelper.get
import com.minosai.local.util.PreferenceHelper.set

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_RECORD_AUDIO = 1
    }

    private val prefs by inject<SharedPreferences>()

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_RECORD_AUDIO)
        } else {
            decideNavigation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_RECORD_AUDIO -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    decideNavigation()
                }
                return
            }
        }
    }

    private fun decideNavigation() {
        when (prefs[Constants.PREF_PROFILE_TYPE, 0]) {
            0 -> startActivity(Intent(this, AuthActivity::class.java))
            1 -> startActivity(Intent(this, PassengerActivity::class.java))
            2 -> startActivity(Intent(this, ConductorActivity::class.java))
            else -> {
                toast("Fatal error occurred")
            }
        }
        finish()
    }
}
