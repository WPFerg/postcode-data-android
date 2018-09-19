package wpferg.postcodes.android

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import wpferg.postcodes.android.viewmodel.PostcodeDetailViewModel

class DetailActivity : AppCompatActivity() {

    var viewModel: PostcodeDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initialiseViewModel()
    }

    fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this)[PostcodeDetailViewModel::class.java]

        viewModel!!.postcode.observe(this, Observer { postcode -> updateTitle(postcode) })
        viewModel!!.setPostcode(intent.getStringExtra(POSTCODE_KEY))
    }

    companion object {
        val POSTCODE_KEY = "POSTCODE"
    }

    fun updateTitle(postcode: String?) {
        title = getString(R.string.postcode_details_for) + " " + postcode
    }

}
