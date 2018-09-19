package wpferg.postcodes.android.fragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_keyvaluepair.view.*
import wpferg.postcodes.android.R

class KeyValuePairListViewAdapter(private val detail: List<Pair<String, String>>, private val context: Context)
    : RecyclerView.Adapter<KeyValuePairListViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_keyvaluepair, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pair = detail[position]
        holder.title.text = pair.first
        holder.value.text = pair.second

        with(holder.mView) {
            tag = pair
        }
    }

    override fun getItemCount(): Int = detail.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val title: TextView = mView.title
        val value: TextView = mView.value

        override fun toString(): String {
            return super.toString() + " '" + value.text + "'"
        }
    }

}
