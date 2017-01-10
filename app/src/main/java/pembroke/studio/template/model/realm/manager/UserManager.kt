package pembroke.studio.template.model.realm.manager

import io.realm.RealmObject
import pembroke.studio.lib.model.RealmManager
import pembroke.studio.lib.model.RealmModelManager
import pembroke.studio.template.model.realm.User
import pembroke.studio.template.model.type.UserType
import pembroke.studio.template.model.type.manager.UserManagerType
import rx.Observable

class UserManager <C>(realmManager: RealmManager, modelClass: Class<C>) : RealmModelManager<C>(realmManager, modelClass), UserManagerType where C: RealmObject, C: UserType {

    override fun all(): Observable<List<UserType>> {
        return query().findAll().asObservable().map { realm.copyFromRealm(it) }
    }

    override fun withId(id: Int): Observable<UserType> {
        return realm.where(User::class.java).equalTo("id", id).findFirst().asObservable<User>().map { realm.copyFromRealm(it) }
    }

    override fun copyOrUpdate(user: UserType) {
        val realmUser = user as? User ?: return
        realm.copyToRealmOrUpdate(realmUser)
    }

    override fun copyOrUpdate(users: List<UserType>) {
        val realmUsers = users.filterIsInstance(User::class.java)
        realm.copyToRealmOrUpdate(realmUsers)
    }
}