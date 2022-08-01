package com.roadster.roam.basesetup.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roadster.roam.basesetup.extensions.ui.dataBind

class GeneralAdapter<T>(
    private val variableId:List<Int>,
    private val layoutResource:List<Int>,
    diffCallback: DiffUtil.ItemCallback<T>,
    private val getItemTypeIndex: (T?) -> Int={0},
    private val listOfClickableIds: List<Int> = mutableListOf()
) : ListAdapter<T, GeneralAdapter<T>.ViewHolder>(diffCallback) {

    /**
     * THis is a generic item click listener used to implement the click of adapter items
     */
    var clickListener: (T, View) -> Unit = { _, _ -> }

    /**
     * THis is a specific item views click listener. used to implement multiple clicks in an item
     */
    var clickListenerSpecific: (T, View, Int) -> Unit = { _, _, _ -> }

    /**
     * This is used for setting the value of the view which are bind to the item. It will provide
     * the bind view back to the invoke location
     */
    var customBindings: (T, bindView: ViewDataBinding) -> Unit = { _, _ -> }
    var customBindingsIndexed: (T, bindView: ViewDataBinding, index:Int) -> Unit = { _, _, _ -> }

    inner class ViewHolder(private val itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        init {
            itemBinding.root.setOnClickListener(this)
            if (listOfClickableIds.isNotEmpty())
                listOfClickableIds.map { id ->
                    itemBinding.root.findViewById<View>(id).setOnClickListener(this)
                }

        }

        private var index: Int = -1
        fun bind(item: T, index: Int) {
            customBindings(item, itemBinding)
            customBindingsIndexed(item, itemBinding,index)
            itemBinding.setVariable(variableId[getItemTypeIndex(getItem(index))], item)
            itemBinding.executePendingBindings()
            this.index = index
        }

        override fun onClick(v: View?) {
            (getItem(layoutPosition)).run {
                if (listOfClickableIds.isEmpty())
                    clickListener(this, v!!)
                else
                    clickListenerSpecific(this, v!!, index)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.dataBind(layoutResource[viewType]))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), position)


    override fun getItemViewType(position: Int): Int {
        return getItemTypeIndex(getItem(position))
    }

    fun getItemAtPosition(index:Int):T?{

        return  getItem(index)
    }

    var dataList:List<T>?=null
    override fun submitList(list: List<T>?) {
        super.submitList(list)
        dataList=list
    }
}