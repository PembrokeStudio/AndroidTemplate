package pembroke.studio.template.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import pembroke.studio.lib.ApiEndpoint
import pembroke.studio.template.dagger.AppModule
import javax.inject.Singleton


@Module(includes = arrayOf(AppModule::class))
class LocalAppModule(application: Application) : AppModule(application) {
    @Provides @Singleton
    fun provideApiEndpoint() =
            ApiEndpoint.Local
}