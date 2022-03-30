package com.andrew.newsreader.viewmodel

import com.andrew.newsreader.data.Item
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.net.URL

class MainViewModel: BaseViewModel() {
    var dataItems: Item? = null
    var gson: Gson = GsonBuilder().setPrettyPrinting().create()

    companion object {
        const val mainViewModelTag = "MainViewModel"
    }

    override fun onCreate() {
        init()
    }

    private fun init() {
        backgroundScope.launch {
            // Fetch data from web site
            loadData()
            parsingComplete.postValue(dataItems)
        }
    }

    private fun loadData() {
        val apiResponse = URL("https://static.mixerbox.com/interview/interview_get_vector.json").readText()
        val jsonObject: JsonObject = Gson().fromJson(apiResponse, JsonObject::class.java)
        val layoutElements = (jsonObject.get("getVector") as JsonObject).toString()
        val typeToken: TypeToken<Item?> = object : TypeToken<Item?>() {}
        dataItems = gson.fromJson(layoutElements, typeToken.type)
    }

}