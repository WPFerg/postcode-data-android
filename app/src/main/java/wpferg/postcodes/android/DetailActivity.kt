package wpferg.postcodes.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*
import wpferg.postcodes.android.domain.PostcodeDetail
import wpferg.postcodes.android.fragment.KeyValuePairListViewAdapter
import wpferg.postcodes.android.http.GetPostcodeDetail
import java.util.logging.Logger

class DetailActivity : AppCompatActivity() {

    val LOGGER = Logger.getLogger(DetailActivity::class.java.name)

    var postcode: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        postcode = intent.getStringExtra(POSTCODE_KEY)
        title = getString(R.string.postcode_details_for) + " " + postcode
        listContainer.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()

        GetPostcodeDetail(postcode!!, this::handlePostcodeDetailSuccess, this::handlePostcodeDetailFailure).execute()
    }

    fun handlePostcodeDetailSuccess(result: PostcodeDetail?) {
        LOGGER.info("Got postcode details " + result)
        if (result != null) {
            listContainer.adapter = KeyValuePairListViewAdapter(convertDetails(result), baseContext)
        }
    }

    fun handlePostcodeDetailFailure() {
        LOGGER.info("Failed to get postcode details")
    }

    fun convertDetails(detail: PostcodeDetail): List<Pair<String, String>> {
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

    companion object {
        val POSTCODE_KEY = "POSTCODE"
    }

}
