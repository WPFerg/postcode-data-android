package wpferg.postcodes.android.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wpferg.postcodes.android.R
import wpferg.postcodes.android.detail.domain.PostcodeDetail
import wpferg.postcodes.android.util.fragment.KeyValuePairListFragment
import wpferg.postcodes.android.util.fragment.LoaderFragment

class PostcodeDetailFragment : Fragment() {

    lateinit var viewModel: PostcodeDetailViewModel

    lateinit var list: KeyValuePairListFragment
    lateinit var loader: LoaderFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!)[PostcodeDetailViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_postcode_detail, container, false)
    }

    override fun onStart() {
        loader = childFragmentManager.findFragmentById(R.id.loader) as LoaderFragment
        list = childFragmentManager.findFragmentById(R.id.list) as KeyValuePairListFragment

        viewModel.loading.observe(activity!!, Observer { loading -> updateSubfragment(loading == null || loading) })
        viewModel.postcodeDetail.observe(activity!!, Observer { postcodeDetail -> updateList(postcodeDetail) })

        super.onStart()
    }

    private fun updateSubfragment(loading: Boolean) {
        val transaction = childFragmentManager.beginTransaction()
        val hidingElement = if (loading) list else loader
        val showingElement = if (loading) loader else list

        if (!hidingElement.isHidden) {
            transaction.hide(hidingElement)
        }

        transaction.show(showingElement)
                .commit()
    }

    private fun updateList(detail: PostcodeDetail?) = list.updateListItems(convertDetails(detail))

    private fun convertDetails(detail: PostcodeDetail?): List<Pair<String, String>> {
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