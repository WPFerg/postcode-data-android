package wpferg.postcodes.android.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import wpferg.postcodes.android.search.domain.SearchPostcodeResponse
import wpferg.postcodes.android.search.http.SearchPostcode

class SearchViewModel : ViewModel() {

    val searchText = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val searchResults = MutableLiveData<SearchPostcodeResponse?>()

    init {
        loading.value = false
        error.value = false
        searchText.value = ""
    }

    fun search(search: String) {
        searchText.value = search
        searchResults.value = null
        error.value = false

        if (search.length > 0) {
            loading.value = true

            SearchPostcode(search, this::handleSearchSuccess, this::handleSearchFailure)
                    .execute()
        }
    }

    fun handleSearchSuccess(result: SearchPostcodeResponse?) {
        loading.value = false
        searchResults.value = result
    }

    fun handleSearchFailure() {
        loading.value = false
        error.value = true
    }

}