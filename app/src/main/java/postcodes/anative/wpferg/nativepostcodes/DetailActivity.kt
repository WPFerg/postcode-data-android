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

class DetailActivity : AppCompatActivity(), GetPostcodeDetail.ResponseHandler {

    val LOGGER = Logger.getLogger(DetailActivity::class.java.name)

    var postcode: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        postcode = intent.getStringExtra("POSTCODE")
        title = getString(R.string.postcode_details_for) + " " + postcode
        listContainer.layoutManager = LinearLayoutManager(this)

        GetPostcodeDetail.search(postcode!!, this)
    }

    override fun handlePostcodeDetailSuccess(result: PostcodeDetail) {
        LOGGER.info("Got postcode details " + result)
        val postcodeDetailFragment = PostcodeDetailFragment();
        listContainer.adapter = PostcodeDetailViewAdapter(result)
    }

    override fun handlePostcodeDetailFailure() {
        LOGGER.info("Failed to get postcode details")
    }
}
