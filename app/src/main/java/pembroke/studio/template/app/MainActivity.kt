package pembroke.studio.template.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import org.jetbrains.anko.find
import pembroke.studio.template.R
import pembroke.studio.template.dagger.AppComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = application as? App ?: return
        component = app.component

        component.inject(this)

        container = find(R.id.activity_container)
        router = Conductor.attachRouter(this, container, savedInstanceState)

        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(MainController()))
        }

    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    lateinit var component: AppComponent

    // Used as the main app window:
    private lateinit var container: ViewGroup
    private lateinit var router: Router
}