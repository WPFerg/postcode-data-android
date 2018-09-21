package wpferg.postcodes.android.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import wpferg.postcodes.android.R

class DetailActivity : AppCompatActivity() {

    lateinit var viewModel: PostcodeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initialiseViewModel()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.elevation = 0f

        pager.adapter = DetailPagerAdapter(supportFragmentManager)
        tabs.setupWithViewPager(pager)
    }

    fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this)[PostcodeDetailViewModel::class.java]

        viewModel.postcode.observe(this, Observer { postcode -> updateTitle(postcode) })
        viewModel.setPostcode(intent.getStringExtra(POSTCODE_KEY))
    }

    companion object {
        val POSTCODE_KEY = "POSTCODE"
    }

    fun updateTitle(postcode: String?) {
        title = postcode
    }

}
