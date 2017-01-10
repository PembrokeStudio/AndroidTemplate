package pembroke.studio.lib.provider

import android.support.v7.app.ActionBar

/**
 * Provides an injectable way to access the Activity's action bar without referencing the Activity.
 */
interface ActionBarProviderType {
    var actionBar: ActionBar?
}