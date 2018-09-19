package wpferg.postcodes.android.application

import android.app.Application
import wpferg.postcodes.android.http.GenericRequest
import java.util.logging.Logger

class NativePostcodesApplication : Application() {

    val LOGGER = Logger.getLogger(NativePostcodesApplication::class.java.name)

    override fun onCreate() {
        super.onCreate()

        GenericRequest.initiateRequestQueue(applicationContext)
        LOGGER.info("Context bound to generic request")
    }

}