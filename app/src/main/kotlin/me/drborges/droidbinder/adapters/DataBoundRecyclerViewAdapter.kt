package me.drborges.droidbinder.adapters

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class RecyclerViewDataProvider<T> {
    abstract val itemBindID: Int
    abstract val items: ObservableList<T>
    abstract fun itemLayoutID(viewType: Int): Int
    open fun itemViewType(position: Int) = 0
}

class DataBoundViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

class DataBoundRecyclerViewAdapter<T>(val dataProvider: RecyclerViewDataProvider<T>) : RecyclerView.Adapter<DataBoundViewHolder>() {
    private var inflater: LayoutInflater? = null

    init {
        dataProvider.items.addOnListChangedCallback(WeakReferenceOnListChangedCallback(this))
    }

    override fun getItemCount() = dataProvider.items.size
    override fun getItemViewType(position: Int) = dataProvider.itemViewType(position)

    override fun onBindViewHolder(holder: DataBoundViewHolder, position: Int) {
        val item = dataProvider.items[position]
        holder.binding.setVariable(dataProvider.itemBindID, item)
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DataBoundViewHolder? {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent?.context)
        }

        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, dataProvider.itemLayoutID(viewType), parent, false)
        return DataBoundViewHolder(binding)
    }
}