package postcodes.anative.wpferg.nativepostcodes.http

import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import postcodes.anative.wpferg.nativepostcodes.domain.GenericResponse
import postcodes.anative.wpferg.nativepostcodes.domain.PostcodeDetail
import postcodes.anative.wpferg.nativepostcodes.domain.SearchPostcodeResponse
import java.lang.reflect.Type
import java.util.logging.Logger

class GetPostcodeDetail {

    companion object {

        private val URL = "http://api.postcodes.io/postcodes/:postcode"
        private val LOGGER = Logger.getLogger(GetPostcodeDetail::class.java.name)

        fun search(text: String, handler: ResponseHandler) {
            val queue = GenericRequest.instance!!.requestQueue!!

            val request = StringRequest(Request.Method.GET, URL.replace(":postcode", text),
                {
                    response ->
                    val type = (object: TypeToken<GenericResponse<PostcodeDetail>>() {}).type
                    handler.handlePostcodeDetailSuccess(
                        Gson().fromJson<GenericResponse<PostcodeDetail>>(response, type).result
                    )
                },
                {
                    error -> handler.handlePostcodeDetailFailure()
                }
            )

            queue.add(request)
        }

    }

    interface ResponseHandler {
        fun handlePostcodeDetailSuccess(result: PostcodeDetail)
        fun handlePostcodeDetailFailure()
    }
}