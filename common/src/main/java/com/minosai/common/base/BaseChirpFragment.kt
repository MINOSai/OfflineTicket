package com.minosai.common.base

import android.util.Log
import com.minosai.common.Constants
import com.minosai.common.toast
import io.chirp.connect.ChirpConnect

abstract class BaseChirpFragment : BaseFragment() {

    protected val chirpSend by lazy {
        getChirpInit().apply {
            val error = start(send = true, receive = false)
            Log.d("CHIRP_SEND_START_ERROR", error.toString())
        }
    }

    protected val chirpReceive by lazy {
        getChirpInit().apply {
            val error = start(send = false, receive = true)
            Log.d("CHIRP_REC_START_ERROR", error.toString())
        }
    }

    private fun getChirpInit() = ChirpConnect(
        requireContext(),
        Constants.CHIRP_APP_KEY,
        Constants.CHIRP_APP_SECRET
    ).apply {
        val error = setConfig(Constants.CHIRP_APP_CONFIG)
        Log.d("CHIRP_CONFIG_ERROR", error.toString())
    }

    override fun onStop() {
        super.onStop()
        stopSdk()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSdk()
        closeSdk()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopSdk()
        try {
            closeSdk()
        } catch (e: Exception) {
            context?.toast("Error while closing chirp")
            e.printStackTrace()
        }
    }

    private fun stopSdk() {
        val sendStopError = chirpSend.stop()
        val recStopError = chirpReceive.stop()
        Log.d("CHIRP_SEND_STOP_ERROR", sendStopError.toString())
        Log.d("CHIRP_REC_STOP_ERROR", recStopError.toString())
    }

    private fun closeSdk() {
        Log.d("CHIRP_CLOSING_SDK", "Closing send and receive")
        chirpSend.close()
        chirpReceive.close()
    }
}