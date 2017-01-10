/**
 * Additional data binding adapters.
 * Make sure to add the app:newField designation to the attrs.xml resource.
 */
package pembroke.studio.lib.extensions

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.Button
import android.widget.EditText
import io.reactivex.Observer
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.textChangedListener

/**
 * Binds an EditText's changes to the provided Observer.
 */
@BindingAdapter("app:textChanges")
fun setTextChanges(editText: EditText, subject: Observer<String>) {
    editText.textChangedListener {
        afterTextChanged { editable ->
            subject.onNext(editable.toString())
        }
    }
}


/**
 * Bind's a View's click events to the provided Observer.
 */
@BindingAdapter("app:clicks")
fun setClicks(view: View, subject: Observer<Unit>) {
    view.onClick { subject.onNext(Unit) }
}


/**
 * Bind's a SwipeRefreshLayout's refresh onRefresh handler to the provided Observer.
 */
@BindingAdapter("app:refresh")
fun setRefresh(swipeRefreshLayout: SwipeRefreshLayout, subject: Observer<Unit>) {
    swipeRefreshLayout.onRefresh { subject.onNext(Unit) }
}


/**
 * Bind's a SwipeRefreshLayout's refresh state to a Boolean value.
 */
@BindingAdapter("app:isRefreshing")
fun setRefreshing(swipeRefreshLayout: SwipeRefreshLayout, subject: Boolean) {
    swipeRefreshLayout.isRefreshing = subject
}