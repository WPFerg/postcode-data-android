package wpferg.postcodes.android.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

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
