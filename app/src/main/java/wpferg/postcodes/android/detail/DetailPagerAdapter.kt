package wpferg.postcodes.android.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class DetailPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val TITLES = listOf("General", "Crime")

    private val postcodeDetailFragment = PostcodeDetailFragment()
    private val postcodeDetailFragment2 = PostcodeDetailFragment()

    // TODO: Make 2 tabs.
    override fun getItem(position: Int): Fragment {
        val fragment = if(position == 0) postcodeDetailFragment else postcodeDetailFragment2
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TITLES[position]
    }

    override fun getCount() = 2

}
