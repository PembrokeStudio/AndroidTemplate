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

package pembroke.studio.lib.rx

import rx.Observable
import rx.Subscriber
import rx.Subscription

fun <T> Observable<T>.subscribeWith(init: __Subscriber<T>.() -> Unit): Subscription {
    val listener = __Subscriber<T>()
    listener.init()

    return subscribe(listener)
}

fun <T> subscriber(init: __Subscriber<T>.() -> Unit): Subscriber<T> {
    val listener = __Subscriber<T>()
    listener.init()

    return listener
}

class __Subscriber<T> : Subscriber<T>() {
    private var _onNext: ((T) -> Unit)? = null
    private var _onError: ((Throwable?) -> Unit)? = null
    private var _onCompleted: (() -> Unit)? = null

    override fun onNext(t: T) {
        _onNext?.invoke(t)
    }

    override fun onError(e: Throwable?) {
        _onError?.invoke(e)
    }

    override fun onCompleted() {
        _onCompleted?.invoke()
    }

    fun onNext(listener: (T) -> Unit) {
        _onNext = listener
    }

    fun onError(listener: (Throwable?) -> Unit) {
        _onError = listener
    }

    fun onCompleted(listener: () -> Unit) {
        _onCompleted = listener
    }
}