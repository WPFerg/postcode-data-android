package wpferg.postcodes.android.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wpferg.postcodes.android.R
import wpferg.postcodes.android.domain.PostcodeDetail
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [KeyValuePairListItemFragment.OnListFragmentInteractionListener] interface.
 */
class KeyValuePairListItemFragment : Fragment() {

    val TITLES = "TITLES"
    val VALUES = "VALUES"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_keyvaluepair_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = KeyValuePairListViewAdapter(reconstitutePairs(), context)
            }
        }
        return view
    }

    fun reconstitutePairs(): List<Pair<String, String>> {
        val titles = arguments!!.getStringArray(TITLES)
        val values = arguments!!.getStringArray(VALUES)

        return titles.zip(values);
    }
}
