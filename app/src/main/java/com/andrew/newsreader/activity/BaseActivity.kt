package com.andrew.newsreader.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.andrew.newsreader.R
import com.andrew.newsreader.databinding.ActivityBaseBinding
import com.andrew.newsreader.viewmodel.BaseViewModel

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    private lateinit var toolbar: Toolbar
    private lateinit var viewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.AppTheme_NoActionBar)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = createViewModel()
        viewModel.onCreate()
    }

    protected abstract fun createViewModel(): BaseViewModel

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}