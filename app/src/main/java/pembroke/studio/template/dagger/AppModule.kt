package pembroke.studio.template.dagger

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pembroke.studio.lib.dagger.Qualifiers
import pembroke.studio.lib.interceptor.ApiRequestInterceptor
import pembroke.studio.lib.preference.StringPreference
import pembroke.studio.lib.preference.StringPreferenceType
import pembroke.studio.lib.provider.CurrentUserProvider
import pembroke.studio.lib.provider.CurrentUserProviderType
import pembroke.studio.template.ApiEndpoint
import pembroke.studio.template.AppUriHandler
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class AppModule(private val application: Application) {
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
    fun provideAppUriHandler(apiEndpoint: ApiEndpoint) =
            AppUriHandler(apiEndpoint)

    @Provides @Singleton
    fun provideOkHttpClient(apiRequestInterceptor: ApiRequestInterceptor) =
            OkHttpClient.Builder()
                    .addInterceptor(apiRequestInterceptor).build()

    @Provides @Singleton
    fun provideApiRequestInterceptor(currentUserProvider: CurrentUserProviderType, appUriHandler: AppUriHandler) =
        ApiRequestInterceptor(currentUserProvider, appUriHandler)
}