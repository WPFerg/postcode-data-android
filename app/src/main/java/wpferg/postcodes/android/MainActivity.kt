package wpferg.postcodes.android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_keyvaluepair.view.*
import wpferg.postcodes.android.domain.SearchPostcodeResponse
import wpferg.postcodes.android.http.SearchPostcode
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    val LOGGER = Logger.getLogger(MainActivity::class.java.name)

    var loadingPostcodeData = false
    var errorLoadingPostcodeData = false
    var postcodeResults: SearchPostcodeResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultView.onItemClickListener = AdapterView.OnItemClickListener {
            adapterView, view, index, p3 -> launchDetailView(adapterView.getItemAtPosition(index) as String)
        }

        searchPostcode.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(sequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onSearchTextChange(sequence!!.toString())
            }
        })
    }

    fun onSearchTextChange(text: String) {
        if (text.length > 0) {
            SearchPostcode(text, this::handleSearchPostcodesSuccess, this::handleSearchPostcodesFailure)
                    .execute()
            loadingPostcodeData = true
            errorLoadingPostcodeData = false
        }

        updateView()
    }

    fun updateView() {
        val hasData = postcodeResults != null && postcodeResults!!.size > 0
        val inputEmpty = searchPostcode.length() == 0

        searchSpinner.visibility = if (loadingPostcodeData) View.VISIBLE else View.GONE
        resultView.visibility = if (!loadingPostcodeData && hasData && !inputEmpty) View.VISIBLE else View.GONE
        noResultsText.visibility = if ((!loadingPostcodeData || errorLoadingPostcodeData) && !hasData)
            View.VISIBLE else View.GONE

        noResultsText.text = if (errorLoadingPostcodeData) "There was an error searching postcodes." else "No results were found"
    }

    fun handleSearchPostcodesSuccess(result: SearchPostcodeResponse?) {
        loadingPostcodeData = false
        postcodeResults = result
        val normalisedResult: List<String> = if (result == null) emptyList() else result
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, normalisedResult)
        resultView!!.adapter = adapter
        updateView()
    }

    fun handleSearchPostcodesFailure() {
        loadingPostcodeData = false
        errorLoadingPostcodeData = true
        updateView()
    }

    fun launchDetailView(postcode: String) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(DetailActivity.POSTCODE_KEY, postcode)
        startActivity(intent)
    }

}
