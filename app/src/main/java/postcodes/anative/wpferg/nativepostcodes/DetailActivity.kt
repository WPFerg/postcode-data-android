package postcodes.anative.wpferg.nativepostcodes

import android.app.FragmentTransaction
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*
import postcodes.anative.wpferg.nativepostcodes.domain.PostcodeDetail
import postcodes.anative.wpferg.nativepostcodes.fragment.PostcodeDetailFragment
import postcodes.anative.wpferg.nativepostcodes.fragment.PostcodeDetailViewAdapter
import postcodes.anative.wpferg.nativepostcodes.http.GetPostcodeDetail
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

        GetPostcodeDetail(postcode!!, this::handlePostcodeDetailSuccess, this::handlePostcodeDetailFailure).execute()
    }

    fun handlePostcodeDetailSuccess(result: PostcodeDetail?) {
        LOGGER.info("Got postcode details " + result)
        if (result != null) {
            listContainer.adapter = PostcodeDetailViewAdapter(result, baseContext)
        }
    }

    fun handlePostcodeDetailFailure() {
        LOGGER.info("Failed to get postcode details")
    }

    companion object {
        val POSTCODE_KEY = "POSTCODE"
    }

}
