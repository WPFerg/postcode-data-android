package wpferg.postcodes.android.util.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
