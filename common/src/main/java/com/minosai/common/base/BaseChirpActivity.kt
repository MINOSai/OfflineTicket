package com.minosai.common.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.minosai.common.Constants
import io.chirp.chirpsdk.ChirpSDK
import io.chirp.chirpsdk.models.ChirpSDKState

open class BaseChirpActivity : AppCompatActivity() {

    var chirp: ChirpSDK? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "init")
        chirp = ChirpSDK(
            this,
            Constants.CHIRP_APP_KEY,
            Constants.CHIRP_APP_SECRET
        ).apply { setConfig(Constants.CHIRP_APP_CONFIG) }
    }

    override fun onStart() {
        super.onStart()
        startSdk()
    }

    override fun onStop() {
        super.onStop()
        stopSdk()
    }

    override fun onDestroy() {
        super.onDestroy()
        chirp?.let {
            try {
                Log.d(TAG, "close")
                it.stop()
                it.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun stopSdk() {
        chirp?.let {
            if (it.getState() > ChirpSDKState.CHIRP_SDK_STATE_STOPPED) {
                Log.d(TAG, "stop")
                it.stop()
            }
        }
    }

    private fun startSdk() {
        chirp?.let {
            if (it.getState() == ChirpSDKState.CHIRP_SDK_STATE_STOPPED) {
                Log.d(TAG, "start")
                it.start()
            }
        }
    }

    companion object {
        private const val TAG = "CHIRP"
    }
}