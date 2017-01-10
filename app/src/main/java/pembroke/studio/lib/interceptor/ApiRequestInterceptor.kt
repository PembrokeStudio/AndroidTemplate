package pembroke.studio.lib.interceptor

import android.net.Uri
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import pembroke.studio.template.AppUriHandler
import pembroke.studio.lib.provider.CurrentUserProviderType

/**
 * An OkHttp Interceptor that adds an API token to any requests heading for the API.
 * Requires a CurrentUserProviderType to get the token for the current logged in User.
 */
class ApiRequestInterceptor(private val currentUserProvider: CurrentUserProviderType, private val appUriHandler: AppUriHandler) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(request(chain.request()))
    }

    /**
     * Checks if the request should be intercepted, and adds the token if so.
     */
    private fun request(initialRequest: Request): Request {
        if (!shouldIntercept(initialRequest)) return initialRequest

        val builder = initialRequest.newBuilder()
        val token = currentUserProvider.token
        if (token != null) {
            builder.header("Authorization", "Bearer ${currentUserProvider.token}")
        }

        return builder.build()
    }

    /**
     *
     */
    private fun shouldIntercept(request: Request): Boolean {
        return appUriHandler.isApiUri(Uri.parse(request.url().toString()))
    }
}