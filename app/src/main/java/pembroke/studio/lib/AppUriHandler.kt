package pembroke.studio.lib

import android.net.Uri

class AppUriHandler(private val apiEndpoint: ApiEndpoint) {

    fun isApiUri(uri: Uri): Boolean {
        return true
    }
}