package wpferg.postcodes.android

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import wpferg.postcodes.android.detail.DetailActivity
import wpferg.postcodes.android.search.SearchActivity

@RunWith(AndroidJUnit4::class)
class SearchInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = IntentsTestRule(SearchActivity::class.java)

    @Test
    fun showsASpinnerWhenSearching() {
        onView(withId(R.id.searchSpinner))
                .check(matches(not(isDisplayed())))

        onView(withId(R.id.searchPostcode))
                .perform(typeText("N"))

        onView(withId(R.id.searchSpinner))
                .check(matches(isDisplayed()))
    }

    @Test
    fun showsAListOfResults() {
        onView(withId(R.id.searchSpinner))
                .check(matches(not(isDisplayed())))

        onView(withId(R.id.searchPostcode))
                .perform(typeText("N"))

        Thread.sleep(2000) // TODO: brittle; investigate how to wait for certain views to be visible

        onView(withText("N10 1AA"))
                .perform(click())

        intended(allOf(hasExtras(
            hasEntry(equalTo(DetailActivity.POSTCODE_KEY), any(String::class.java))
        )))
    }
}