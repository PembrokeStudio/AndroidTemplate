package pembroke.studio.lib.model

import io.realm.RealmObject
import io.realm.RealmResults
import rx.Observable

abstract class RealmModelManager <C: RealmObject>(realmManager: RealmManager, private val modelClass: Class<C>) {
    protected val realm = realmManager.realm
    protected fun query() = realm.where(modelClass)

    protected fun detachObject(e: Observable<C>): Observable<C> = e.map { realm.copyFromRealm(it) }
    protected fun detachObjects(e: Observable<RealmResults<C>>): Observable<List<C>> = e.map { realm.copyFromRealm(it) }
}
