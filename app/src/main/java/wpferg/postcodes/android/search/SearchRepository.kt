package wpferg.postcodes.android.search

import wpferg.postcodes.android.search.domain.SearchPostcodeResponse
import wpferg.postcodes.android.search.http.SearchPostcode
import javax.inject.Inject

class SearchRepository @Inject constructor() {

    fun search(query: String, successCallback: (SearchPostcodeResponse?) -> Unit, failureCallback: () -> Unit) =
        SearchPostcode(query, successCallback, failureCallback).execute()

}