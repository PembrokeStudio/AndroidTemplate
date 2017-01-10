package pembroke.studio.template.dagger

import pembroke.studio.template.app.App
import pembroke.studio.template.app.MainActivity

interface AppGraph {
    fun inject(app: App)
    fun inject(mainActivity: MainActivity)
}