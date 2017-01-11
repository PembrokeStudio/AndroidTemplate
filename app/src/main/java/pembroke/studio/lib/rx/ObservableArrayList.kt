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

package pembroke.studio.lib.rx

import rx.Observable
import rx.subjects.BehaviorSubject
import java.util.*
import java.util.function.Predicate
import java.util.function.UnaryOperator

/**
 * A simple Observable ArrayList, backed by a BehaviorSubject and a standard Array List.
 * Be careful sharing the Observable received from asObservable(); look up publish(), connect(),
 * and share() in the Rx docs if you plan on using it more than once.
 */
class ObservableArrayList <T> : ArrayList<T>() {
    private val subject = BehaviorSubject.create<List<T>>()

    fun asObservable(): Observable<List<T>> {
        return subject
    }

    override fun clear() {
        super.clear()
        subject.onNext(this)
    }

    override fun add(element: T): Boolean {
        val result = super.add(element)
        subject.onNext(this)

        return result
    }

    override fun add(index: Int, element: T) {
        super.add(index, element)
        subject.onNext(this)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val result = super.addAll(elements)
        subject.onNext(this)

        return result
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val result =  super.addAll(index, elements)
        subject.onNext(this)

        return result
    }

    override fun remove(element: T): Boolean {
        val result =  super.remove(element)
        subject.onNext(this)

        return result
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        val result =  super.removeAll(elements)
        subject.onNext(this)

        return result
    }

    override fun removeAt(index: Int): T {
        val result =  super.removeAt(index)
        subject.onNext(this)

        return result
    }

    override fun removeIf(filter: Predicate<in T>?): Boolean {
        val result =  super.removeIf(filter)
        subject.onNext(this)

        return result
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex)
        subject.onNext(this)
    }

    override fun replaceAll(operator: UnaryOperator<T>?) {
        super.replaceAll(operator)
        subject.onNext(this)
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val result =  super.retainAll(elements)
        subject.onNext(this)

        return result
    }

    override fun trimToSize() {
        super.trimToSize()
        subject.onNext(this)
    }

    override fun sort(c: Comparator<in T>?) {
        super.sort(c)
        subject.onNext(this)
    }

    override fun set(index: Int, element: T): T {
        val result =  super.set(index, element)
        subject.onNext(this)

        return result
    }
}