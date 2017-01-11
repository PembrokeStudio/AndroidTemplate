package pembroke.studio.template.dagger

import pembroke.studio.template.app.App
import pembroke.studio.template.app.MainActivity
import pembroke.studio.template.app.MainController


interface AppGraph {
    fun inject(app: App)
    fun inject(mainActivity: MainActivity)

    fun inject(mainController: MainController)
}