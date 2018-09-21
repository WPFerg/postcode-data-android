package wpferg.postcodes.android.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import wpferg.postcodes.android.detail.domain.PostcodeSummary
import wpferg.postcodes.android.util.fragment.KeyValuePairListFragment
import wpferg.postcodes.android.util.fragment.LoaderFragment

class PostcodeDetailFragment : LoaderFragment() {

    private lateinit var viewModel: PostcodeDetailViewModel
    private val list = KeyValuePairListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!)[PostcodeDetailViewModel::class.java]
    }

    override fun onStart() {
        connect(viewModel.loading, list)
        viewModel.postcodeSummary.observe(activity!!, Observer { summary -> if (summary != null) updateList(summary) })

        super.onStart()
    }

    private fun updateList(detail: PostcodeSummary) = list.updateListItems(detail)

}