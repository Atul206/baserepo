package com.roadster.roam.basesetup.adapter

import android.graphics.Rect
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*


enum class MarginStrategy {
    ONLY_TOP,
    ONLY_RIGHT,
    ONLY_BOTTOM,
    ALL_BUT_TOP,
    ALL_BUT_BOTTOM,
    ALL_BUT_TOP_AND_BOTTOM,
    ALL_SIDES,
}


@BindingAdapter(value = ["bindPagingVerticalList", "items"], requireAll = false)
fun <T> RecyclerView.bindPagingRecyclerViewAdapter(adapter: ConcatAdapter, list: List<T>?) {
    this.run {
        this.configureVerticalPagingList(adapter)
        this.adapter = adapter
    }
    if (this.adapter is ListAdapter<*, *>) {
        (adapter as ListAdapter<T, *>).submitList(list)

    }
}

fun RecyclerView.configureVerticalPagingList(
    adapter: ConcatAdapter,
    margin: Int = 0.toPx(),

    ) {

    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    setHasFixedSize(true)
    addItemMargins(margin, MarginStrategy.ONLY_BOTTOM)
    this.adapter = adapter

}


@BindingAdapter(value = ["haveNoMargin","isNotFixedItemSize","showVerticalList", "items"], requireAll = false)
fun <T> RecyclerView.bindRecyclerViewAdapter(haveNoMargin:Boolean,isNotFixedItemSize:Boolean,adapter: ListAdapter<*, *>, list: List<T>?) {
    this.run {

        var margin: Int = 8.toPx()
        if(haveNoMargin) {

            margin=0.toPx()
        }

        this.configureVerticalList(adapter = adapter, margin=margin,isNotFixedItem = isNotFixedItemSize)
//        if(haveFirstItemMargin){
//            this.configureVerticalList(adapter, 0)
//        }
        this.adapter = adapter
    }
    //todo it's for static list only
    if (this.adapter is ListAdapter<*, *>) {
        //(adapter as ListAdapter<T, *>).submitList(list)

    }
}



fun <T, VH : RecyclerView.ViewHolder> RecyclerView.configureVerticalList(
    adapter: ListAdapter<T, VH>,
    margin: Int = 8.toPx(),
    isNotFixedItem:Boolean
    ) {

    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    setHasFixedSize(!isNotFixedItem)
    addItemMargins(margin, MarginStrategy.ONLY_BOTTOM)
    this.adapter = adapter

}

fun RecyclerView.addItemMargins(
    spaceHeight: Int,
    marginStrategy: MarginStrategy = MarginStrategy.ONLY_TOP,
    extraMarginForFirstItem: Boolean = false
) {

    if (extraMarginForFirstItem) {
        addItemDecoration(ExtraTopMarginItemDecoration(spaceHeight, marginStrategy))
    } else {
        addItemDecoration(MarginItemDecoration(spaceHeight, marginStrategy))
    }

}


class ExtraStartMarginItemDecoration(
    private val spaceHeight: Int
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            with(outRect) { left = spaceHeight + 20 }
        }
    }
}


class ExtraTopMarginItemDecoration(
    private val spaceHeight: Int,
    private val marginStrategy: MarginStrategy,
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position < 2) {
            with(outRect) { top = spaceHeight + 20 }

        }

    }
}

class MarginFirstAndLastItemDecoration(
   private val marginStart: Int = 0.toPx(),
   private val marginEnd: Int = 0.toPx(),

) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)

        if(position==0){

            with(outRect) {

                left = marginStart
            }
        }

        if(position==state.itemCount -1){

            with(outRect) {
                right = marginEnd
            }
        }



    }
}


class MarginItemDecoration(
    private val spaceHeight: Int,
    private val marginStrategy: MarginStrategy,
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
//        val itemCount = state.itemCount

        when (marginStrategy) {
            MarginStrategy.ONLY_TOP -> with(outRect) { top = spaceHeight }
            MarginStrategy.ONLY_RIGHT -> with(outRect) { right = spaceHeight }
            MarginStrategy.ALL_BUT_TOP -> if (position > 0) with(outRect) { top = spaceHeight }
            MarginStrategy.ALL_SIDES -> if (position > -1) with(outRect) {
                top = spaceHeight
                left = spaceHeight
                right = spaceHeight
                bottom = spaceHeight
            }
            MarginStrategy.ONLY_BOTTOM -> with(outRect) { bottom = spaceHeight }
            MarginStrategy.ALL_BUT_BOTTOM -> TODO()
            MarginStrategy.ALL_BUT_TOP_AND_BOTTOM -> TODO()
        }

    }
}