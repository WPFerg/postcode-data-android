package wpferg.postcodes.android.util.fragment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_loader.*
import wpferg.postcodes.android.R

abstract class LoaderFragment : Fragment() {

    var loadComplete = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loader, container, false)
    }

    protected fun connect(data: MutableLiveData<Boolean?>, replacement: Fragment) {
        data.observe(this, Observer { loading -> if (loading != null && !loading) hasLoaded(replacement) })
    }

    protected fun hasLoaded(replacement: Fragment) {
        if (!loadComplete) {
            (view as ViewGroup).removeView(progress_bar)

            childFragmentManager.beginTransaction()
                .add(R.id.container, replacement)
                .commit()

            loadComplete = true
        }
    }
}
