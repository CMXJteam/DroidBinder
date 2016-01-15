package me.drborges.droidbinder.bindings

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import me.drborges.droidbinder.R
import me.drborges.droidbinder.adapters.RecyclerViewDataProvider
import me.drborges.droidbinder.adapters.DataBoundRecyclerViewAdapter
import me.drborges.droidbinder.observables.ResponsiveObservable
import me.drborges.droidbinder.watchers.SimpleTextWatchers

object TwoWayDataBindings {

    @BindingAdapter("binding")
    fun bindEditText(view: EditText, observable: ResponsiveObservable<String>) {
        if (view.getTag(R.id.binded) == null) {
            view.setTag(R.id.binded, true)
            view.addTextChangedListener(object : SimpleTextWatchers() {
                override fun afterTextChanged(s: Editable) {
                    observable.set(s.toString())
                }
            })
        }

        val newValue = observable.get()
        if (view.text.toString() != newValue) {
            view.setText(newValue)
        }
    }

    @BindingAdapter("android:text")
    fun bindEditText(view: TextView, observable: ResponsiveObservable<String>) {
        view.text = observable.get()
    }

    @BindingAdapter("binding")
    fun <T> bindRecyclerView(view: RecyclerView, dataProvider: RecyclerViewDataProvider<T>) {
        view.adapter = DataBoundRecyclerViewAdapter(dataProvider)
    }
}
