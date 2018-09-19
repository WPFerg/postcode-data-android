package wpferg.postcodes.android.search.http

import com.google.gson.reflect.TypeToken
import wpferg.postcodes.android.util.domain.PostcodeResponse
import wpferg.postcodes.android.search.domain.SearchPostcodeResponse
import wpferg.postcodes.android.util.http.GenericRequest

class SearchPostcode(val query: String, val successCallback: (SearchPostcodeResponse?) -> Unit, val failureCallback: () -> Unit)
    : GenericRequest<PostcodeResponse<SearchPostcodeResponse?>>() {

    private val URL = "http://api.postcodes.io/postcodes/:postcode/autocomplete"

    override fun execute() = get(URL.replace(":postcode", query))
    override fun requestSuccess(result: PostcodeResponse<SearchPostcodeResponse?>) = successCallback(result.result)
    override fun requestError() = failureCallback()
    override fun typeToken(): TypeToken<PostcodeResponse<SearchPostcodeResponse?>> = object: TypeToken<PostcodeResponse<SearchPostcodeResponse?>>() {}

}