package wpferg.postcodes.android.application

import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationInjectionModule::class
])
interface ApplicationInjectionComponent {
    fun inject(app: NativePostcodesApplication)
}