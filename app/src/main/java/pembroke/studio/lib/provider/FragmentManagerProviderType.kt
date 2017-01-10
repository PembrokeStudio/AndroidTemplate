package pembroke.studio.lib.provider

import android.app.FragmentManager

/**
 * Provides an injectable way to access the Activity's Fragment Manager without
 * referencing the Activity.
 */
interface FragmentManagerProviderType {
    var fragmentManager: FragmentManager?
}