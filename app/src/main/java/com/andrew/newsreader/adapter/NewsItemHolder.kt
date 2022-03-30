package com.andrew.newsreader.adapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andrew.newsreader.R

/**
 * Created by andrew.liu on 2022/1/18.
 */
class NewsItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.title)
    var time: TextView = itemView.findViewById(R.id.time)
    var imageArea: ViewGroup = itemView.findViewById(R.id.image_area)
    var image: ImageView = itemView.findViewById(R.id.image)
    var source: TextView = itemView.findViewById(R.id.source)
}