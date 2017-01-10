package pembroke.studio.lib.model

import rx.Observable

interface ModelManagerType <C: ModelType>  {
    fun all(): Observable<List<C>>
    fun withId(id: Int): Observable<C>

    fun copyOrUpdate(user: C)
    fun copyOrUpdate(users: List<C>)
}