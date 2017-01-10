package pembroke.studio.lib.provider

import com.google.gson.Gson
import io.reactivex.subjects.BehaviorSubject
import org.jetbrains.anko.AnkoLogger
import pembroke.studio.lib.preference.StringPreferenceType

/**
 * An implementation of the CurrentUserProviderType that uses StringPreferenceTypes to store and
 * retrieve values.
 */
class CurrentUserProvider(private val tokenPreference: StringPreferenceType,
                          private val userPreference: StringPreferenceType,
                          private val gson: Gson)
    : CurrentUserProviderType, AnkoLogger {


    override fun login(user: Any, token: String) {
        tokenPreference.set(token)

        this.user.onNext(user)
    }

    override fun logout() {
        userPreference.delete()
        tokenPreference.delete()

        user.onNext(null)
    }

    override val token: String?
        get() = tokenPreference.get()


    override val user = BehaviorSubject.create<Any?>()

    init {
        // Loads the User into the BehaviorSubject.
        user.skip(1).filter { it != null }
                .subscribe{ user: Any? -> userPreference.set(gson.toJson(user, Any::class.java)) }

        user.onNext(gson.fromJson(userPreference.get(), Any::class.java))
    }
}