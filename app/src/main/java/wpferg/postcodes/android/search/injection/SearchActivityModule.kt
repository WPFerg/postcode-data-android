package wpferg.postcodes.android.search.injection

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import wpferg.postcodes.android.search.SearchActivity

@Module(subcomponents = [SearchActivityComponent::class])
abstract class SearchActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(SearchActivity::class)
    abstract fun bindSearchActivityInjectorFactory(builder: SearchActivityComponent.Builder): AndroidInjector.Factory<out Activity>

}