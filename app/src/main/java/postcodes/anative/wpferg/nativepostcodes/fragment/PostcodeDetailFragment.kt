package postcodes.anative.wpferg.nativepostcodes.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import postcodes.anative.wpferg.nativepostcodes.R
import postcodes.anative.wpferg.nativepostcodes.domain.PostcodeDetail

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PostcodeDetailFragment.OnListFragmentInteractionListener] interface.
 */
class PostcodeDetailFragment : Fragment() {

    val POSTCODE_DETAIL = "POSTCODE_DETAIL"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_postcodedetail_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = PostcodeDetailViewAdapter(arguments!!.get(POSTCODE_DETAIL) as PostcodeDetail)
            }
        }
        return view
    }
}
