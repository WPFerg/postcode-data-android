package postcodes.anative.wpferg.nativepostcodes.fragment


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_postcodedetail.view.*
import postcodes.anative.wpferg.nativepostcodes.R
import postcodes.anative.wpferg.nativepostcodes.domain.PostcodeDetail

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class PostcodeDetailViewAdapter(private val detail: PostcodeDetail)
    : RecyclerView.Adapter<PostcodeDetailViewAdapter.ViewHolder>() {

    val convertedDetail = convertDetail(detail)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_postcodedetail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = convertedDetail[position]
        holder.title.text = item.first
        holder.value.text = item.second

        with(holder.mView) {
            tag = item
        }
    }

    override fun getItemCount(): Int = convertedDetail.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val title: TextView = mView.title
        val value: TextView = mView.value

        override fun toString(): String {
            return super.toString() + " '" + value.text + "'"
        }
    }

    private fun convertDetail(detail: PostcodeDetail): List<Pair<String, String>> {
        return listOf(
            Pair("Quality", detail.quality.toString()),
            Pair("Parish", detail.parish),
            Pair("Country", detail.country),
            Pair("Parliamentary Constituency", detail.parliamentaryConstituency),
            Pair("European Electoral Region", detail.europeanElectoralRegion),
            Pair("Clinical Commissioning Group", detail.ccg),
            Pair("Primary Care Trust", detail.primaryCareTrust)
        )
    }
}
