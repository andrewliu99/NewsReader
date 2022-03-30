package com.andrew.newsreader.data

import com.google.gson.annotations.SerializedName

class Meta(@SerializedName("section") val section: String = "",
           @SerializedName("category") val category: MutableList<String> = arrayListOf())

class Header(@SerializedName("type") val type: String = "",
             @SerializedName("title") val title: String = "",
             @SerializedName("_meta") val meta: Meta? = null)

class Appearance(@SerializedName("mainTitle") val mainTitle: String = "",
                 @SerializedName("subTitle") val subTitle: String = "",
                 @SerializedName("thumbnail") val thumbnail: String = "",
                 @SerializedName("subscript") val subscript: String = "")

class Extra(@SerializedName("created") val created: Long = -1)

class NewsSubItem(@SerializedName("type") var type: String = "",
                  @SerializedName("source") var source: String = "",
                  @SerializedName("ref") var ref: String = "",
                  @SerializedName("title") var title: String = "",
                  @SerializedName("style") var style: String = "",
                  @SerializedName("appearance") var appearance: Appearance? = null,
                  @SerializedName("extra") var extra: Extra? = null,
                  @SerializedName("_meta") var meta: Meta? = null,
                  var isDivider: Boolean = true) {
}

class NewsItem(@SerializedName("type") var type: String = "",
               @SerializedName("source") var source: String = "",
               @SerializedName("ref") var ref: String = "",
               @SerializedName("title") var title: String = "",
               @SerializedName("style") var style: String = "",
               @SerializedName("appearance") var appearance: Appearance? = null,
               @SerializedName("extra") var extra: Extra? = null,
               @SerializedName("_meta") var meta: Meta? = null,
               @SerializedName("items") val items: MutableList<NewsSubItem> = arrayListOf())

class Item(@SerializedName("items") val items: MutableList<NewsItem> = arrayListOf())
class Vector(@SerializedName("getVector") val vector: String = "")