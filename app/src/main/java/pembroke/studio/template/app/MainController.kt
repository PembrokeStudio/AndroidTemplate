package pembroke.studio.template.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.find
import pembroke.studio.lib.conductor.BaseController
import pembroke.studio.lib.provider.CurrentUserProviderType
import pembroke.studio.template.R
import pembroke.studio.template.dagger.AppComponent
import javax.inject.Inject

class MainController(args: Bundle? = null) : BaseController(args) {
    override fun createView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.view_container, container, false)
    }

    override fun onInject(component: AppComponent) {
        component.inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        root = view.find(R.id.view_container)

        currentUserProvider.user
    }

    override fun handleBack(): Boolean {
        if (getChildRouter(root).handleBack()) {
            return true
        }

        return super.handleBack()
    }

    lateinit var root: ViewGroup

    @Inject lateinit var currentUserProvider: CurrentUserProviderType
}