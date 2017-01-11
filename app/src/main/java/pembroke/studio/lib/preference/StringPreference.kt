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

package pembroke.studio.lib.preference

import android.content.SharedPreferences
import pembroke.studio.lib.preference.StringPreferenceType


/**
 * An implementation of the StringPreferenceType interface using Android's SharedPreferences.
 */
class StringPreference constructor(private val sharedPreferences: SharedPreferences,
                                   private val key: String,
                                   private val defaultValue: String? = null)
    : StringPreferenceType {

    override fun get(): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    override fun isSet(): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun set(value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun delete() {
        sharedPreferences.edit().remove(key).apply()
    }
}