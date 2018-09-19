package postcodes.anative.wpferg.nativepostcodes.http

import com.google.gson.reflect.TypeToken
import postcodes.anative.wpferg.nativepostcodes.domain.PostcodeResponse
import postcodes.anative.wpferg.nativepostcodes.domain.SearchPostcodeResponse

class SearchPostcode(val query: String, val successCallback: (SearchPostcodeResponse?) -> Unit, val failureCallback: () -> Unit)
    : GenericRequest<PostcodeResponse<SearchPostcodeResponse?>>() {

    private val URL = "http://api.postcodes.io/postcodes/:postcode/autocomplete"

    override fun execute() = get(URL.replace(":postcode", query))
    override fun requestSuccess(result: PostcodeResponse<SearchPostcodeResponse?>) = successCallback(result.result)
    override fun requestError() = failureCallback()
    override fun typeToken(): TypeToken<PostcodeResponse<SearchPostcodeResponse?>> = object: TypeToken<PostcodeResponse<SearchPostcodeResponse?>>() {}

}