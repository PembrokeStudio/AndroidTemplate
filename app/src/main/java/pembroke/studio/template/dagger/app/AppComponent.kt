package pembroke.studio.template.dagger.app

import dagger.Component
import pembroke.studio.template.app.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}