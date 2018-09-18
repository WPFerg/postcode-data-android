package postcodes.anative.wpferg.nativepostcodes.http

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import postcodes.anative.wpferg.nativepostcodes.domain.SearchPostcodeResponse
import java.util.logging.Logger

class SearchPostcode {

    companion object {

        private val URL = "http://api.postcodes.io/postcodes/:postcode/autocomplete"
        private val LOGGER = Logger.getLogger(SearchPostcode::class.java.name)

        fun search(text: String, handler: ResponseHandler) {
            val queue = GenericRequest.instance!!.requestQueue!!
            LOGGER.info("Searching for postcodes " + text)

            val request = StringRequest(Request.Method.GET, URL.replace(":postcode", text),
                {
                    response -> handler.handleSearchPostcodesSuccess(
                        Gson().fromJson(response, SearchPostcodeResponse::class.java).result
                    )
                },
                {
                    error -> handler.handleSearchPostcodesFailure()
                }
            )

            queue.add(request)
        }

    }

    interface ResponseHandler {
        fun handleSearchPostcodesSuccess(result: Array<String>?)
        fun handleSearchPostcodesFailure()
    }
}