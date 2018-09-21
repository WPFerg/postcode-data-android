package wpferg.postcodes.android.util.http

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.logging.Logger

abstract class GenericRequest<T> () {

    companion object {
        private var queue: RequestQueue? = null
        private val LOGGER = Logger.getLogger(javaClass.name)

        fun initiateRequestQueue(context: Context) {
            queue = Volley.newRequestQueue(context)
        }
    }

    private val TYPE = typeToken().type

    private fun unmarshalResponse(response: String): T = Gson().fromJson<T>(response, TYPE)

    private fun request(uri: String, method: Int) {
        LOGGER.fine("Submitting request to " + uri)
        val request = StringRequest(method, uri,
            { response -> processResponse(response) },
            { response -> processError(response) }
        )

        queue!!.add(request)
    }

    fun get(uri: String) = request(uri, Request.Method.GET)

    private fun processResponse(response: String) {
        LOGGER.fine("Request successful")
        requestSuccess(unmarshalResponse(response))
    }

    private fun processError(response: VolleyError) {
        LOGGER.info("Request failed: " + response.message)
        requestError()
    }

    abstract fun typeToken(): TypeToken<T>
    abstract fun execute()
    abstract fun requestSuccess(result: T)
    abstract fun requestError()

}