package wpferg.postcodes.android.application

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasDispatchingActivityInjector
import wpferg.postcodes.android.util.http.GenericRequest
import java.util.logging.Logger
import javax.inject.Inject

class NativePostcodesApplication : Application(), HasDispatchingActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    val LOGGER = Logger.getLogger(NativePostcodesApplication::class.java.name)

    override fun onCreate() {
        super.onCreate()

        GenericRequest.initiateRequestQueue(applicationContext)
        LOGGER.info("Context bound to generic request")

        DaggerApplicationInjectionComponent.create()
            .inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = injector

}