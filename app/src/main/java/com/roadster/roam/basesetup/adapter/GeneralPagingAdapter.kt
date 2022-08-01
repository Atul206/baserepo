package com.roadster.roam.basesetup.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.roadster.roam.basesetup.extensions.ui.dataBind

class GeneralPagingAdapter<T : Any>(
    private val variableId: List<Int>,
    private val layoutResource: List<Int>,
    diffCallback: DiffUtil.ItemCallback<T>,
    private val getItemTypeIndex:(T?)->Int,
    private val listOfClickableIds: List<Int> = mutableListOf(),

    ) : PagingDataAdapter<T, GeneralPagingAdapter<T>.ViewHolder>(diffCallback) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.apply {

            holder.bind(this, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.dataBind(layoutResource[viewType]))
    }



    /**
     * This is a generic item click listener used to implement the click of adapter items
     */
    var clickListener: (T?, View) -> Unit = { _, _ -> }

    /**
     * THis is a specific item views click listener. used to implement multiple clicks in an item
     */
    var clickListenerSpecific: (T?, View, Int) -> Unit = { _, _, _ -> }

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


    override fun getItemViewType(position: Int): Int {
        return getItemTypeIndex(getItem(position))
    }

    fun getItemAtPosition(index:Int):T?{
        return if(index<itemCount) {
            getItem(index)
        }else{

            null
        }
    }


}