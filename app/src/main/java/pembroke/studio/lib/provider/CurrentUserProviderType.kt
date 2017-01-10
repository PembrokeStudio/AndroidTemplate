package pembroke.studio.lib.provider

import io.reactivex.Observer

/**
 * Provides an interface for interacting with the current User.
 */
interface CurrentUserProviderType {
    /** Stores the logged in User w/ the provided token. **/
    fun login(user: Any, token: String)

    /** Removes the currently logged in User if there is one. **/
    fun logout()

    /** Readonly accesss to the current token String **/
    val token: String?

    /** Readonly acccess to the current User object. **/
    val user: Observer<Any?>
}