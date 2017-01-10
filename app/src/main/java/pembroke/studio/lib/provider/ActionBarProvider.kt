package pembroke.studio.lib.provider

import android.support.v7.app.ActionBar
import pembroke.studio.lib.provider.ActionBarProviderType

/**
 * An implementation of the ActionBarProviderType that just provides a way to access and modify
 * the provided ActionBar.
 */
class ActionBarProvider : ActionBarProviderType {
    override var actionBar: ActionBar? = null
}