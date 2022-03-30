package com.andrew.newsreader.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.andrew.newsreader.R
import com.andrew.newsreader.util.TimeUtils.getDateTimeFormatWithHourMinute
import com.andrew.newsreader.data.NewsSubItem
import com.andrew.newsreader.util.ImageUtils


class NewsAdapter(private val data: Any?): Adapter<RecyclerView.ViewHolder>() {
    lateinit var context : Context

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_ITEM = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        return when(viewType) {
            ITEM_VIEW_TYPE_HEADER -> {
                NewsHeaderHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_header, parent, false)
                )
            }
            else -> {
                NewsItemHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_multi_lines, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: NewsSubItem = (data as List<*>)[position] as NewsSubItem
        when (holder) {
            is NewsHeaderHolder -> {
                val itemHolder = holder as NewsHeaderHolder
                itemHolder.title.text = item.title
            }
            else -> {
                val itemHolder = holder as NewsItemHolder
                if (item.extra != null) {
                    itemHolder.time.text =
                        getDateTimeFormatWithHourMinute(item.extra!!.created * 1000)
                }
                itemHolder.title.text = item.appearance!!.mainTitle
                itemHolder.source.text = item.appearance!!.subTitle

                val img = item.appearance!!.thumbnail
                when {
                    img.isNotEmpty() -> {
                        ImageUtils.getRoundRectImageFromUrl(img, itemHolder.image, 15)
                    } else -> {
                        itemHolder.image.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return (data as List<*>).size
    }

    override fun getItemViewType(position: Int): Int {
        val item: NewsSubItem = (data as List<*>)[position] as NewsSubItem
        if(item.isDivider) {
            return ITEM_VIEW_TYPE_HEADER
        }
        return ITEM_VIEW_TYPE_ITEM
    }
}