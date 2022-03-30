package com.andrew.newsreader

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andrew.newsreader.activity.BaseActivity
import com.andrew.newsreader.adapter.NewsAdapter
import com.andrew.newsreader.data.*
import com.andrew.newsreader.databinding.ActivityMainBinding
import com.andrew.newsreader.viewmodel.BaseViewModel
import com.andrew.newsreader.viewmodel.MainViewModel
import java.lang.ref.WeakReference

var contextRef: WeakReference<Context>? = null

class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    var recyclerView: RecyclerView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init contextRef
        contextRef = WeakReference(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView

        setSupportActionBar(binding.toolbar)

        mainViewModel.parsingComplete.observe(this) {
            // update UI
            updateUI(it)
        }

    }

    override fun createViewModel(): BaseViewModel {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return mainViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI(item: Item) {

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.bottom = resources.getDimensionPixelSize(R.dimen.common_div_item_height)
            }
        })

        val dataList: MutableList<NewsSubItem> = arrayListOf()
        item.items.forEach { newsItem ->
            if (newsItem.items.size == 0 && newsItem.appearance == null) {
                dataList.add(NewsSubItem(newsItem.type, "", "", newsItem.title, "", null, null, null))
            } else if (newsItem.appearance != null) {
                dataList.add(NewsSubItem(newsItem.type, newsItem.source, newsItem.ref, newsItem.title, newsItem.style, newsItem.appearance, newsItem.extra, newsItem.meta, false))
            }else {
                newsItem.items.forEach { subItem ->
                    subItem.isDivider = false
                    dataList.add(subItem)
                }
            }
        }
        recyclerView.adapter = NewsAdapter(dataList)
    }

}