package wpferg.postcodes.android.util.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_loader.*
import wpferg.postcodes.android.R

abstract class LoaderFragment : Fragment() {

    var loadComplete = false
    lateinit var isLoading: MutableLiveData<Boolean?>
    lateinit var replacement: Fragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loader, container, false)
    }

    protected fun connect(data: MutableLiveData<Boolean?>, replacement: Fragment) {
        this.isLoading = data
        this.replacement = replacement
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
