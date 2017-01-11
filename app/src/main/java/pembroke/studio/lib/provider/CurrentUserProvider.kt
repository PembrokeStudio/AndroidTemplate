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

package pembroke.studio.lib.provider

import com.google.gson.Gson
import io.reactivex.subjects.BehaviorSubject
import org.jetbrains.anko.AnkoLogger
import pembroke.studio.lib.preference.StringPreferenceType
import pembroke.studio.template.model.UserType


/**
 * An implementation of the CurrentUserProviderType that uses StringPreferenceTypes to store and
 * retrieve values.
 */
class CurrentUserProvider(private val tokenPreference: StringPreferenceType,
                          private val userPreference: StringPreferenceType,
                          private val gson: Gson)
    : CurrentUserProviderType, AnkoLogger {


    override fun login(user: UserType, token: String) {
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


    override val user = BehaviorSubject.create<UserType?>()

    init {
        // Loads the User into the BehaviorSubject.
        user.skip(1).filter { it != null }
                .subscribe{ user: Any? -> userPreference.set(gson.toJson(user, Any::class.java)) }

        user.onNext(gson.fromJson(userPreference.get(), UserType::class.java))
    }
}