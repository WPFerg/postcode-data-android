package wpferg.postcodes.android.application

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationInjectionModule(val application: NativePostcodesApplication) {
    @Provides
    @Singleton
    fun provideApplication(): NativePostcodesApplication = application
}