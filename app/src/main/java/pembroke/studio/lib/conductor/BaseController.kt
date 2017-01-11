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
package pembroke.studio.lib.conductor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.rxlifecycle2.RxController
import pembroke.studio.template.app.MainActivity
import pembroke.studio.template.dagger.AppComponent


abstract class BaseController(args: Bundle? = null) : RxController(args) {
    private var injected = false

    override final fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        inject(context = inflater.context)

        return createView(inflater, container)
    }

    private fun inject(context: Context) {
        if (!injected) {
            injected = true

            val activity = context as? MainActivity ?: return
            onInject(activity.component)
        }
    }

    protected abstract fun createView(inflater: LayoutInflater, container: ViewGroup): View
    protected abstract fun onInject(component: AppComponent)
}