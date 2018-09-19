package wpferg.postcodes.android.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import wpferg.postcodes.android.R
import wpferg.postcodes.android.detail.domain.PostcodeDetail
import wpferg.postcodes.android.util.fragment.KeyValuePairListFragment

class PostcodeDetailFragment : KeyValuePairListFragment() {

    var viewModel: PostcodeDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!)[PostcodeDetailViewModel::class.java]

        viewModel!!.postcodeDetail.observe(activity!!, Observer { detail -> updateListItems(convertDetails(detail)) })
    }

    fun convertDetails(detail: PostcodeDetail?): List<Pair<String, String>> {
        if (detail == null) {
            return emptyList()
        }

        return listOf(
            Pair(getString(R.string.detail_quality), detail.quality.toString()),
            Pair(getString(R.string.detail_parish), detail.parish),
            Pair(getString(R.string.detail_country), detail.country),
            Pair(getString(R.string.detail_constituency), detail.parliamentaryConstituency),
            Pair(getString(R.string.detail_european_constituency), detail.europeanElectoralRegion),
            Pair(getString(R.string.detail_ccg), detail.ccg),
            Pair(getString(R.string.detail_pct), detail.primaryCareTrust)
        )
    }

}