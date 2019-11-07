package com.minosai.common.base

import android.os.Bundle
import io.chirp.chirpsdk.ChirpSDK
import io.chirp.chirpsdk.models.ChirpSDKState

abstract class BaseChirpFragment : BaseFragment() {

    protected var chirp: ChirpSDK? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        chirp = ChirpSDK(
//            requireContext(),
//            Constants.CHIRP_APP_KEY,
//            Constants.CHIRP_APP_SECRET
//        ).apply { setConfig(Constants.CHIRP_APP_CONFIG) }

        initializeChirp()
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
                it.stop()
            }
        }
    }

    private fun startSdk() {
        chirp?.let {
            if (it.getState() == ChirpSDKState.CHIRP_SDK_STATE_STOPPED) {
                it.start()
            }
        }
    }


//    private var chirp: ChirpSDK? = null
//
//    protected val chirpSend by lazy {
//        getChirpInit().apply {
//            val error = start(send = true, receive = false)
//            Log.d("CHIRP_SEND_START_ERROR", error.toString() + " in ${getFragmentName()}")
//        }
//    }
//
//    protected val chirpReceive by lazy {
//        getChirpInit().apply {
//            val error = start(send = false, receive = true)
//            Log.d("CHIRP_REC_START_ERROR", error.toString() + " in ${getFragmentName()}")
//        }
//    }
//
//    private fun getChirpInit(): ChirpSDK {
//        if (chirp == null) {
//            chirp = ChirpSDK(
//                requireContext(),
//                Constants.CHIRP_APP_KEY,
//                Constants.CHIRP_APP_SECRET
//            ).apply {
//                val error = setConfig(Constants.CHIRP_APP_CONFIG)
//                Log.d("CHIRP_CONFIG_ERROR", error.toString() + " in ${getFragmentName()}")
//            }
//        }
//        return chirp!!
//    }
//
//    fun stopSdk() {
//        Log.d("CHIRP", "Stopping send and receive in ${getFragmentName()}")
////        chirpSend.stop()
////        chirpReceive.stop()
//    }
//    fun startSdk() {
//        Log.d("CHIRP", "Starting send and receive in ${getFragmentName()}")
////        chirpSend.start(send = true, receive = false)
////        chirpReceive.start(send = false, receive = true)
//    }
//
//    private fun closeSdk() {
//        Log.d("CHIRP", "Closing send and receive")
//        chirpSend.close()
//        chirpReceive.close()
//    }

    abstract fun getFragmentName(): String

    abstract fun initializeChirp()
}