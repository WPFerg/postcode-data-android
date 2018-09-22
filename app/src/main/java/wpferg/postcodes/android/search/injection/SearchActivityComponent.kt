package wpferg.postcodes.android.search.injection

import dagger.Subcomponent
import dagger.android.AndroidInjector
import wpferg.postcodes.android.search.SearchActivity

@Subcomponent
interface SearchActivityComponent : AndroidInjector<SearchActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SearchActivity>()

}