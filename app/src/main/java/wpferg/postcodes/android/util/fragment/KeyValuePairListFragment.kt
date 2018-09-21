package wpferg.postcodes.android.util.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wpferg.postcodes.android.R

class KeyValuePairListFragment : Fragment() {

    var list: List<Pair<String, String>> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_keyvaluepair_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
            }
        }
        updateViewAdapter(view)
        return view
    }

    fun updateListItems(pairs: List<Pair<String, String>>) {
        list = pairs
        updateViewAdapter(view)
    }

    fun updateViewAdapter(view: View?) {
        if (view is RecyclerView) {
            view.adapter = KeyValuePairListViewAdapter(list, context!!)
        }
    }

}
