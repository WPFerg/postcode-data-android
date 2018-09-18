package postcodes.anative.wpferg.nativepostcodes.http

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class GenericRequest {

    companion object {
        var instance: GenericRequest? = null

        fun initiateRequestQueue(context: Context) {
            instance = GenericRequest(context)
        }
    }

    var requestQueue: RequestQueue? = null

    private constructor(context: Context) {
        requestQueue = Volley.newRequestQueue(context)
    }

}