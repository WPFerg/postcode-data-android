package wpferg.postcodes.android.application

import dagger.Module
import dagger.android.ContributesAndroidInjector
import wpferg.postcodes.android.search.SearchActivity

@Module
abstract class ActivityInjectionContributor {

    @ContributesAndroidInjector // can use (modules = []) here.
    abstract fun contributeSearchActivityInjector() : SearchActivity

}