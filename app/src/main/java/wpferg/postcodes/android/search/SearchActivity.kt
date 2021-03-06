package wpferg.postcodes.android.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import wpferg.postcodes.android.R
import wpferg.postcodes.android.detail.DetailActivity
import wpferg.postcodes.android.search.domain.SearchPostcodeResponse
import java.util.logging.Logger
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: SearchViewModel.Factory

    val LOGGER = Logger.getLogger(SearchActivity::class.java.name)

    var viewModel: SearchViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_search)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)

        viewModel!!.loading.observe(this, Observer { loading -> updateView() })
        viewModel!!.error.observe(this, Observer { error -> updateView() })
        viewModel!!.searchResults.observe(this, Observer { results -> handleSearchPostcodesSuccess(results) })

        resultView.onItemClickListener = AdapterView.OnItemClickListener {
            adapterView, view, index, p3 -> launchDetailView(adapterView.getItemAtPosition(index) as String)
        }

        searchPostcode.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel!!.search(sequence!!.toString())
            }
        })
    }

    fun updateView() {
        val loading = viewModel!!.loading.value!!
        val error = viewModel!!.error.value!!
        val inputText = viewModel!!.searchText.value!!
        val results = viewModel!!.searchResults.value

        val hasData = results != null && results.isNotEmpty()
        val inputEmpty = inputText.isEmpty()

        searchSpinner.visibility = if (loading) View.VISIBLE else View.GONE
        resultView.visibility = if (!loading && hasData && !inputEmpty) View.VISIBLE else View.GONE
        noResultsText.visibility = if ((!loading || error) && !hasData && !inputEmpty) View.VISIBLE else View.GONE

        noResultsText.text = getString(if (error) R.string.search_error else R.string.search_no_results)
    }

    fun handleSearchPostcodesSuccess(result: SearchPostcodeResponse?) {
        val normalisedResult: List<String> = if (result == null) emptyList() else result
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, normalisedResult)
        resultView!!.adapter = adapter
        updateView()
    }

    fun launchDetailView(postcode: String) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(DetailActivity.POSTCODE_KEY, postcode)
        startActivity(intent)
    }

}
