package wpferg.postcodes.android.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import wpferg.postcodes.android.R
import wpferg.postcodes.android.application.NativePostcodesApplication
import wpferg.postcodes.android.detail.domain.PostcodeDetail
import wpferg.postcodes.android.detail.domain.PostcodeSummary
import wpferg.postcodes.android.detail.http.PostcodeDetailRequest

class PostcodeDetailViewModel(application: Application) : AndroidViewModel(application) {

    val application = getApplication<NativePostcodesApplication>()
    val postcode = MutableLiveData<String>()
    val postcodeDetail = MutableLiveData<PostcodeDetail?>()
    val postcodeSummary = MutableLiveData<PostcodeSummary>()
    val loading = MutableLiveData<Boolean?>()

    init {
        loading.value = false
        postcodeSummary.value = emptyList()
    }

    fun setPostcode(definedPostcode: String) {
        if (!postcode.value.equals(definedPostcode, true)) {
            postcode.value = definedPostcode
            PostcodeDetailRequest(definedPostcode, this::handlePostcodeDetailSuccess, this::handlePostcodeDetailFailure).execute()
        }
    }

    private fun handlePostcodeDetailSuccess(result: PostcodeDetail?) {
        postcodeDetail.value = result
        postcodeSummary.value = calculatePostcodeSummary(result)
        loading.value = false
    }

    private fun handlePostcodeDetailFailure() {
        loading.value = false
    }

    private fun calculatePostcodeSummary(detail: PostcodeDetail?): PostcodeSummary {
        if (detail == null) {
            return emptyList()
        }

        return listOf(
            Pair(application.getString(R.string.detail_quality), detail.quality.toString()),
            Pair(application.getString(R.string.detail_parish), detail.parish),
            Pair(application.getString(R.string.detail_country), detail.country),
            Pair(application.getString(R.string.detail_constituency), detail.parliamentaryConstituency),
            Pair(application.getString(R.string.detail_european_constituency), detail.europeanElectoralRegion),
            Pair(application.getString(R.string.detail_ccg), detail.ccg),
            Pair(application.getString(R.string.detail_pct), detail.primaryCareTrust)
        )
    }

}