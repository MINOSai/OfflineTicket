package com.minosai.common.base

import com.minosai.common.Constants
import io.chirp.connect.ChirpConnect
import java.lang.Exception

abstract class BaseChirpFragment : BaseFragment() {

    protected val chirpSend by lazy {
        getChirpInit().apply {
            start(send = true, receive = false)
        }
    }

    protected val chirpReceive by lazy {
        getChirpInit().apply {
            start(send = false, receive = true)
        }
    }

    private fun getChirpInit() = ChirpConnect(
        requireContext(),
        Constants.CHIRP_APP_KEY,
        Constants.CHIRP_APP_SECRET
    ).apply {
        setConfig(Constants.CHIRP_APP_CONFIG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        chirpSend.stop()
        chirpReceive.stop()
        try {
            chirpSend.close()
            chirpReceive.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}