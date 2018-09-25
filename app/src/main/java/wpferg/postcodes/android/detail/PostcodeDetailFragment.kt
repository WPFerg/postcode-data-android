package wpferg.postcodes.android.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import wpferg.postcodes.android.detail.domain.PostcodeSummary
import wpferg.postcodes.android.util.fragment.KeyValuePairListFragment
import wpferg.postcodes.android.util.fragment.LoaderFragment

class PostcodeDetailFragment : LoaderFragment() {

    private lateinit var viewModel: PostcodeDetailViewModel
    private val list = KeyValuePairListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!)[PostcodeDetailViewModel::class.java]
        viewModel.postcodeSummary.observe(activity!!, Observer { summary -> if (summary != null) updateList(summary) })
        connect(viewModel.loading, list)

        // Restore any cached data.
        val postcodeSummary = viewModel.postcodeSummary.value!!
        if (!viewModel.loading.value!! && postcodeSummary.isNotEmpty()) {
            updateList(viewModel.postcodeSummary.value!!)
        }
    }

    private fun updateList(detail: PostcodeSummary) = list.updateListItems(detail)

}