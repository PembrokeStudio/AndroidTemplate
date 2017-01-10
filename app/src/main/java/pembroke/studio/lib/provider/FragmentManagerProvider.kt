package pembroke.studio.lib.provider

import android.app.FragmentManager
import pembroke.studio.lib.provider.FragmentManagerProviderType

/**
 * An implementation of the FragmentManagerProviderType that just provides a way to access and modify
 * the provided FragmentManager.
 */
class FragmentManagerProvider : FragmentManagerProviderType {
    override var fragmentManager: FragmentManager? = null
}