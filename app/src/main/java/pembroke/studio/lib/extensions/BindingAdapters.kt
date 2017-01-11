/*
    Copyright (c) 2017 Pembroke Studio, LLC

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
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
 * Additional data binding adapters.
 * Make sure to add the app:newField designation to the attrs.xml resource.
 */

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