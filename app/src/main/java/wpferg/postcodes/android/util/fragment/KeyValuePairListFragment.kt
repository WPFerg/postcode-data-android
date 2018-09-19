package wpferg.postcodes.android.util.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wpferg.postcodes.android.R

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [KeyValuePairListFragment.OnListFragmentInteractionListener] interface.
 */
open class KeyValuePairListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_keyvaluepair_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
            }
        }
        return view
    }

    fun updateListItems(pairs: List<Pair<String, String>>) {
        if (view is RecyclerView) {
            (view as RecyclerView).adapter = KeyValuePairListViewAdapter(pairs, context!!)
        }
    }

}
