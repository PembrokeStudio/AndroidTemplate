package pembroke.studio.template.dagger

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(LocalAppModule::class))
interface AppComponent : AppGraph