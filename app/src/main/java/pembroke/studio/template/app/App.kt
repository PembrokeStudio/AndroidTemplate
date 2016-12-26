package pembroke.studio.template.app

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import net.danlew.android.joda.JodaTimeAndroid

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()

        Realm.setDefaultConfiguration(config)

        JodaTimeAndroid.init(this)

//        component = DaggerAppComponent.builder()
//                .appModule(AppModule(this))
//                .build()
    }
}