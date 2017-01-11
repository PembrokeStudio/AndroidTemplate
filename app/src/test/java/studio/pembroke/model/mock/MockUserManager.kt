package studio.pembroke.model.mock

import pembroke.studio.lib.rx.ObservableArrayList
import pembroke.studio.template.model.UserType
import pembroke.studio.template.model.type.manager.UserManagerType
import rx.Observable

/**
 * Example of how to mock a ModelManager.
 * This Manager is backed by a simple Observable Array List.
 */
class MockUserManager : UserManagerType {
    private var users = ObservableArrayList<UserType>()

    override fun all(): Observable<List<UserType>> {
        return users.asObservable()
    }

    override fun copyOrUpdate(user: UserType) {
        users.add(user)
    }

    override fun copyOrUpdate(users: List<UserType>) {
        this.users.addAll(users)
    }

    override fun withId(id: Int): Observable<UserType> {
        val user = this.users.find { it.id == id }

        return user?.asObservable() ?: Observable.empty()
    }
}