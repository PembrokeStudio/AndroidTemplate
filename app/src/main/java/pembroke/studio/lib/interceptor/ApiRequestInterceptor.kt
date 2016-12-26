package pembroke.studio.lib.interceptor

import android.net.Uri
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import pembroke.studio.lib.provider.CurrentUserProviderType
import pembroke.studio.lib.AppUri

class ApiRequestInterceptor(private val currentUserProvider: CurrentUserProviderType) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(request(chain.request()))
    }

    private fun request(initialRequest: Request): Request {
        if (!shouldIntercept(initialRequest)) return initialRequest

        val builder = initialRequest.newBuilder()
        val token = currentUserProvider.token
        if (token != null) {
            builder.header("Authorization", "Bearer ${currentUserProvider.token}")
        }

        return builder.build()
    }

    private fun shouldIntercept(request: Request): Boolean {
        return AppUri.isApiUri(Uri.parse(request.url().toString()))
    }
}