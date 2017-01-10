package pembroke.studio.template.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import pembroke.studio.template.ApiEndpoint
import pembroke.studio.template.dagger.AppModule
import javax.inject.Singleton


@Module(includes = arrayOf(AppModule::class))
class LocalAppModule {
    @Provides @Singleton
    fun provideApiEndpoint() =
            ApiEndpoint.Local
}