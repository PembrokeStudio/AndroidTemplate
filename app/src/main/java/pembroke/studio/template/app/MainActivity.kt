package pembroke.studio.template.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pembroke.studio.lib.provider.CurrentUserProviderType
import pembroke.studio.template.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = application as? App ?: return
        app.component.inject(this)
    }

    @Inject lateinit var currentUserProvider: CurrentUserProviderType
}
