package pembroke.studio.template.model.realm

import io.realm.RealmObject
import pembroke.studio.template.model.type.UserType

open class User : RealmObject(), UserType {
    override var id: Int = 0
}