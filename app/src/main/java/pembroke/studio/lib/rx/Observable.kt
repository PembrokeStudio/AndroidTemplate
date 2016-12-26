package pembroke.studio.lib.rx

import rx.Observable

/**
 * Combines the latest result from the target Observable with a Completable Observable 'event'.
 * Useful for combining inputs with actions, like clicks.
 */
fun <T, R> Observable<T>.joinEvent(event: Observable<R>): Observable<T> {
    val shared = this.share()
    val combine: (T, R) -> T = { t, r -> t }

    return shared.join(event, { value -> shared }, { value -> Observable.empty<R>()}, combine)
}
