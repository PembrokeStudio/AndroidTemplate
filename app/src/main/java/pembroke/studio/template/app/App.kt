package pembroke.studio.template.app

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import net.danlew.android.joda.JodaTimeAndroid
import pembroke.studio.template.dagger.AppComponent
import pembroke.studio.template.dagger.AppModule
import pembroke.studio.template.dagger.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        component.inject(this)

        Realm.setDefaultConfiguration(config)

        JodaTimeAndroid.init(this)
    }
}