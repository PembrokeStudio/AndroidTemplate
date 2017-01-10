package pembroke.studio.template.dagger

import dagger.Component
import pembroke.studio.template.dagger.AppGraph
import pembroke.studio.template.dagger.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(StagingAppModule::class))
interface AppComponent : AppGraph