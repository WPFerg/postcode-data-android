package postcodes.anative.wpferg.nativepostcodes.appliication

import android.app.Application
import android.util.Log
import postcodes.anative.wpferg.nativepostcodes.http.GenericRequest
import java.util.logging.Logger

class NativePostcodesApplication : Application() {

    val LOGGER = Logger.getLogger(NativePostcodesApplication::class.java.name)

    override fun onCreate() {
        super.onCreate()

        GenericRequest.initiateRequestQueue(applicationContext)
        LOGGER.info("Context bound to generic request")
    }

}