package postcodes.anative.wpferg.nativepostcodes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import postcodes.anative.wpferg.nativepostcodes.http.SearchPostcode
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), SearchPostcode.ResponseHandler {

    var searchText: EditText? = null
    var progressBar: ProgressBar? = null
    var listView: ListView? = null
    val LOGGER = Logger.getLogger(MainActivity::class.java.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchText = findViewById<EditText>(R.id.searchPostcode)
        progressBar = findViewById<ProgressBar>(R.id.searchSpinner)
        listView = findViewById<ListView>(R.id.resultView)

        listView!!.onItemClickListener = AdapterView.OnItemClickListener {
            adapterView, view, index, p3 -> launchDetailView(adapterView.getItemAtPosition(index) as String)
        }

        searchText!!.addTextChangedListener(object: TextWatcher {
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
        SearchPostcode.search(text, this)
    }

    override fun handleSearchPostcodesSuccess(result: Array<String>?) {
        loading(false)
        val normalisedResult: Array<String> = if (result == null) emptyArray() else result
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, normalisedResult)
        listView!!.adapter = adapter
    }

    override fun handleSearchPostcodesFailure() {
        LOGGER.info("Postcode search failure")
        loading(false)
    }

    fun loading(value: Boolean) {
        progressBar!!.visibility = if (value) View.VISIBLE else View.GONE
        listView!!.visibility = if (value) View.GONE else View.VISIBLE
    }

    fun launchDetailView(postcode: String) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(DetailActivity.POSTCODE_KEY, postcode)
        startActivity(intent)
    }

}
