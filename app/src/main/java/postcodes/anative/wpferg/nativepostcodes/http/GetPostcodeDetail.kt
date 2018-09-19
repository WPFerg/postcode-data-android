package postcodes.anative.wpferg.nativepostcodes.http

import com.google.gson.reflect.TypeToken
import postcodes.anative.wpferg.nativepostcodes.domain.PostcodeDetail
import postcodes.anative.wpferg.nativepostcodes.domain.PostcodeResponse

class GetPostcodeDetail(val postcode: String, val successCallback: (PostcodeDetail?) -> Unit, val failureCallback: () -> Unit)
    : GenericRequest<PostcodeResponse<PostcodeDetail?>>() {

    private val URL = "http://api.postcodes.io/postcodes/:postcode"

    override fun execute() = get(URL.replace(":postcode", postcode))
    override fun requestSuccess(result: PostcodeResponse<PostcodeDetail?>) = successCallback(result.result)
    override fun requestError() = failureCallback()
    override fun typeToken(): TypeToken<PostcodeResponse<PostcodeDetail?>> = object: TypeToken<PostcodeResponse<PostcodeDetail?>>() {}

}