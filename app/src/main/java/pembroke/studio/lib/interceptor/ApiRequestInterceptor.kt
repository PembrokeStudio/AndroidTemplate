/*
    Copyright (c) 2017 Pembroke Studio, LLC

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
 */

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