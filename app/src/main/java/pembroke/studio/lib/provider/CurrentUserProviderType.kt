package pembroke.studio.lib.provider

import io.reactivex.Observer

/**
 * Provides an interface for interacting with the current User:
 * Based on Kickstarter's OSS Android
 */
interface CurrentUserProviderType {
    fun login(user: Any, token: String)
    fun logout()

    val token: String?

    val user: Observer<Any?>
}