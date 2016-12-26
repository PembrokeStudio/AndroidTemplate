package pembroke.studio.lib.view

import android.app.FragmentManager
import android.util.TypedValue
import android.view.View
import android.view.ViewManager
import org.jetbrains.anko.editText
import org.jetbrains.anko.singleLine
import java.util.concurrent.atomic.AtomicInteger

private val sNextGeneratedId = AtomicInteger(1)

/**
 * Taken from API 17 code.
 */
fun newId(): Int {
    while (true) {
        val result = sNextGeneratedId.get()
        // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
        var newValue = result + 1
        if (newValue > 0x00FFFFFF) newValue = 1 // Roll over to 1, not 0.
        if (sNextGeneratedId.compareAndSet(result, newValue)) {
            return result
        }
    }
}

fun ViewManager.textInput(theme: Int = 0) = editText(theme) {
    singleLine = true
}

fun View.attribute(id: Int): Int {
    val value = TypedValue()
    context.theme.resolveAttribute(id, value, true)

    return value.data
}

fun FragmentManager.showProgressView(title: String, message: String) {
    val progressDialogFragment = findFragmentByTag(ProgressDialogFragment.fragmentTag)
    if (progressDialogFragment == null) {
        val fragment = ProgressDialogFragment(title, message)
        fragment.isCancelable = false

        beginTransaction()
                .add(fragment, ProgressDialogFragment.fragmentTag)
                .commitAllowingStateLoss()
    }
}

fun FragmentManager.hideProgressView() {
    val progressDialogFragment = findFragmentByTag(ProgressDialogFragment.fragmentTag)
    if (progressDialogFragment != null) {
        beginTransaction()
                .remove(progressDialogFragment)
                .commitAllowingStateLoss()
    }
}