package wpferg.postcodes.android.application

import dagger.Component
import dagger.android.AndroidInjectionModule
import wpferg.postcodes.android.search.injection.SearchActivityModule

@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationInjectionModule::class,
    SearchActivityModule::class
])
interface ApplicationInjectionComponent {
    fun inject(app: NativePostcodesApplication)
}