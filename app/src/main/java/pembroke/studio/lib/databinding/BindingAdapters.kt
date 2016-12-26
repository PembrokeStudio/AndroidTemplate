package pembroke.studio.lib.databinding

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.textChangedListener
import rx.Observer

@BindingAdapter("app:textChanges")
fun setTextChanges(editText: EditText, subject: Observer<String>) {
    editText.textChangedListener {
        afterTextChanged { editable ->
            subject.onNext(editable.toString())
        }
    }
}

@BindingAdapter("app:clicks")
fun setClicks(view: View, subject: Observer<Unit>) {
    view.onClick { subject.onNext(Unit) }
}

@BindingAdapter("app:refresh")
fun setRefresh(swipeRefreshLayout: SwipeRefreshLayout, subject: Observer<Unit>) {
    swipeRefreshLayout.onRefresh { subject.onNext(Unit) }
}

@BindingAdapter("app:isRefreshing")
fun setRefreshing(swipeRefreshLayout: SwipeRefreshLayout, subject: Boolean) {
    swipeRefreshLayout.isRefreshing = subject
}

@BindingAdapter("app:calcClicks")
fun setCalcClicks(button: Button, subject: Observer<String>) {
    button.onClick {
        val b = it as? Button ?: return@onClick
        subject.onNext(b.text.toString())
    }
}