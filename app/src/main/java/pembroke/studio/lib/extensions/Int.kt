package pembroke.studio.lib.extensions

import android.content.res.Resources

/**
 * Converts an Int value in DiP to Px.
 */
val Int.px: Float
    get() = (this / Resources.getSystem().displayMetrics.density)

/**
 * Converts an Int value in Px to DiP.
 */
val Int.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)