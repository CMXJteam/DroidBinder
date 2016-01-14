package me.drborges.droidbinder.observables

import android.databinding.BaseObservable

class ResponsiveObservable<T>(initial: T, val updateCallback: (T) -> Unit) : BaseObservable() {
    private var value : T = initial

    fun get(): T = value

    fun set(value: T) {
        this.value = value
        notifyChange()
        updateCallback(value)
    }
}