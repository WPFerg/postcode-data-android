package postcodes.anative.wpferg.nativepostcodes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import postcodes.anative.wpferg.nativepostcodes.domain.SearchPostcodeResponse
import postcodes.anative.wpferg.nativepostcodes.http.SearchPostcode
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    val LOGGER = Logger.getLogger(MainActivity::class.java.name)

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
        loading(true)
        SearchPostcode(text, this::handleSearchPostcodesSuccess, this::handleSearchPostcodesFailure)
            .execute()
    }

    fun handleSearchPostcodesSuccess(result: SearchPostcodeResponse?) {
        loading(false)
        val normalisedResult: List<String> = if (result == null) emptyList() else result
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, normalisedResult)
        resultView!!.adapter = adapter
    }

    fun handleSearchPostcodesFailure() {
        LOGGER.info("Postcode search failure")
        loading(false)
    }

    fun loading(value: Boolean) {
        searchSpinner.visibility = if (value) View.VISIBLE else View.GONE
        resultView.visibility = if (value) View.GONE else View.VISIBLE
    }

    fun launchDetailView(postcode: String) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(DetailActivity.POSTCODE_KEY, postcode)
        startActivity(intent)
    }

}
