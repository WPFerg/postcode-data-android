package wpferg.postcodes.android.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wpferg.postcodes.android.search.domain.SearchPostcodeResponse
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

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
        val sendRequest = search.isNotEmpty() && !searchText.value.equals(search, true)
        searchText.value = search

        if (sendRequest) {
            loading.value = true
            searchResults.value = null
            error.value = false

            searchRepository.search(search, this::handleSearchSuccess, this::handleSearchFailure)
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

    class Factory @Inject constructor(val searchViewModel: SearchViewModel): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
                return modelClass.cast(searchViewModel)!!
            }
            throw IllegalArgumentException("Unknown class: " + modelClass.name)
        }

    }

}