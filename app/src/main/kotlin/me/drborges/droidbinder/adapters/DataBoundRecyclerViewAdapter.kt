package me.drborges.droidbinder.adapters

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class DataBoundViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

class DataBoundRecyclerViewAdapter<T>(val bindingVariableID: Int, val itemLayout: Int, val items: ObservableList<T>) : RecyclerView.Adapter<DataBoundViewHolder>() {

    private var inflater: LayoutInflater? = null

    override fun getItemCount() = items.size

    init {
        items.addOnListChangedCallback(WeakReferenceOnListChangedCallback(this))
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        val item = items[position]
        holder.binding.setVariable(bindingVariableID, item)
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataBoundViewHolder? {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent?.context)
        }

        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, itemLayout, parent, false)
        return DataBoundViewHolder(binding)
    }
}