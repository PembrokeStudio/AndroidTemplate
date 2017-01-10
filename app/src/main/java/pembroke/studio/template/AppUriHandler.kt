package pembroke.studio.template

import android.net.Uri
import pembroke.studio.template.ApiEndpoint

class AppUriHandler(private val apiEndpoint: ApiEndpoint) {

    fun isApiUri(uri: Uri): Boolean {
        return true
    }
}