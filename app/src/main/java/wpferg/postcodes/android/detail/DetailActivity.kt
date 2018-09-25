package wpferg.postcodes.android.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
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

        val viewPager = pager as ViewPager
        viewPager.adapter = DetailPagerAdapter(supportFragmentManager)
        (tabs as TabLayout).setupWithViewPager(viewPager)
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
