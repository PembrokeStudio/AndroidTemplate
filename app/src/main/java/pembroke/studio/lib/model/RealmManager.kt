package pembroke.studio.lib.model

import android.os.Looper
import io.realm.Realm

class RealmManager {
    val realm: Realm by lazy {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw IllegalThreadStateException("Can't get main thread Realm instance from background thread!")
        }

        Realm.getDefaultInstance()
    }

    fun close() {
        realm.close()
    }
}