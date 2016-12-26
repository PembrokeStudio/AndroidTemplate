package pembroke.studio.lib.android

import android.content.res.Resources

val Int.px: Float
    get() = (this / Resources.getSystem().displayMetrics.density)
val Int.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)