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