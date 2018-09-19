package wpferg.postcodes.android.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import wpferg.postcodes.android.detail.domain.PostcodeDetail
import wpferg.postcodes.android.detail.http.PostcodeDetailRequest

class PostcodeDetailViewModel() : ViewModel() {

    val postcode = MutableLiveData<String>()
    val postcodeDetail = MutableLiveData<PostcodeDetail?>()
    val loading = MutableLiveData<Boolean?>()

    init {
        loading.value = false
    }

    fun setPostcode(definedPostcode: String) {
        postcode.value = definedPostcode
        PostcodeDetailRequest(definedPostcode, this::handlePostcodeDetailSuccess, this::handlePostcodeDetailFailure).execute()
    }

    fun handlePostcodeDetailSuccess(result: PostcodeDetail?) {
        postcodeDetail.value = result
        loading.value = false
    }

    fun handlePostcodeDetailFailure() {
        loading.value = false
    }



}