package wpferg.postcodes.android.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import wpferg.postcodes.android.domain.PostcodeDetail
import wpferg.postcodes.android.http.GetPostcodeDetail

class PostcodeDetailViewModel() : ViewModel() {

    val postcode = MutableLiveData<String>()
    val postcodeDetail = MutableLiveData<PostcodeDetail?>()
    val loading = MutableLiveData<Boolean?>()

    init {
        loading.value = false
    }

    fun setPostcode(definedPostcode: String) {
        postcode.value = definedPostcode
        GetPostcodeDetail(definedPostcode, this::handlePostcodeDetailSuccess, this::handlePostcodeDetailFailure).execute()
    }

    fun handlePostcodeDetailSuccess(result: PostcodeDetail?) {
        postcodeDetail.value = result
        loading.value = false
    }

    fun handlePostcodeDetailFailure() {
        loading.value = false
    }



}