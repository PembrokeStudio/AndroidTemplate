package pembroke.studio.lib.view

import android.app.Dialog
import android.app.DialogFragment
import android.app.ProgressDialog
import android.os.Bundle


class ProgressDialogFragment(private val title: String, private val message: String) : DialogFragment() {

    companion object {
        val fragmentTag = "ProgressDialogFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = ProgressDialog(activity, theme)

        dialog.setTitle(title)
        dialog.setMessage(message)

        dialog.isIndeterminate = true
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)

        return dialog
    }
}