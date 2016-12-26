package pembroke.studio.template.dagger.app

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pembroke.studio.lib.provider.CurrentUserProvider
import pembroke.studio.lib.provider.CurrentUserProviderType
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import pembroke.studio.template.dagger.Qualifiers
import pembroke.studio.lib.ApiEndpoint
import pembroke.studio.lib.interceptor.ApiRequestInterceptor
import pembroke.studio.lib.preference.StringPreference
import pembroke.studio.lib.preference.StringPreferenceType
import pembroke.studio.lib.provider.ActionBarProvider
import pembroke.studio.lib.provider.ActionBarProviderType
import pembroke.studio.lib.provider.FragmentManagerProvider
import pembroke.studio.lib.provider.FragmentManagerProviderType
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Provides @Singleton
    fun provideApplication(): Application {
        return application
    }

    @Provides @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(application)

    @Provides @Singleton @Qualifiers.TokenPreference
    fun provideTokenPreference(sharedPreferences: SharedPreferences): StringPreferenceType =
            StringPreference(sharedPreferences, "access_token")

    @Provides @Singleton @Qualifiers.UserPreference
    fun provideUserPreference(sharedPreferences: SharedPreferences): StringPreferenceType =
            StringPreference(sharedPreferences, "user")

    @Provides @Singleton
    fun provideCurrentUser(@Qualifiers.TokenPreference tokenPreference: StringPreferenceType,
                           @Qualifiers.UserPreference userPreference: StringPreferenceType,
                           gson: Gson): CurrentUserProviderType =
            CurrentUserProvider(tokenPreference, userPreference, gson)

    @Provides @Singleton
    fun provideGson() = GsonBuilder()
            .create()

    @Provides @Singleton
    fun provideRetrofit(endpoint: ApiEndpoint, client: OkHttpClient, gson: Gson) =
            Retrofit.Builder()
                .baseUrl(endpoint.url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

    @Provides @Singleton
    fun provideApiEndpoint() =
            ApiEndpoint.Local

    @Provides @Singleton
    fun provideOkHttpClient(apiRequestInterceptor: ApiRequestInterceptor) =
            OkHttpClient.Builder()
                    .addInterceptor(apiRequestInterceptor).build()

    @Provides @Singleton
    fun provideApiRequestInterceptor(currentUserProvider: CurrentUserProviderType) =
        ApiRequestInterceptor(currentUserProvider)

    @Provides @Singleton
    fun provideActionBarProvider() : ActionBarProviderType = ActionBarProvider()

    @Provides @Singleton
    fun provideFragmentManagerProvider() : FragmentManagerProviderType = FragmentManagerProvider()
}