package postcodes.anative.wpferg.nativepostcodes.fragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_postcodedetail.view.*
import postcodes.anative.wpferg.nativepostcodes.R
import postcodes.anative.wpferg.nativepostcodes.domain.PostcodeDetail

class PostcodeDetailViewAdapter(private val detail: PostcodeDetail, private val context: Context)
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
            Pair(getString(R.string.detail_quality), detail.quality.toString()),
            Pair(getString(R.string.detail_parish), detail.parish),
            Pair(getString(R.string.detail_country), detail.country),
            Pair(getString(R.string.detail_constituency), detail.parliamentaryConstituency),
            Pair(getString(R.string.detail_european_constituency), detail.europeanElectoralRegion),
            Pair(getString(R.string.detail_ccg), detail.ccg),
            Pair(getString(R.string.detail_pct), detail.primaryCareTrust)
        )
    }

    private fun getString(asset: Int): String = context.getString(asset)

}
