package com.harsh.databindingwithrecyclerview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.harsh.databindingwithrecyclerview.R
import com.harsh.databindingwithrecyclerview.adapter.NewsAdapter
import com.harsh.databindingwithrecyclerview.base.Source
import com.harsh.databindingwithrecyclerview.data.APIResult
import com.harsh.databindingwithrecyclerview.databinding.ActivityMainBinding
import com.harsh.databindingwithrecyclerview.extension.isInternetAvailable
import com.harsh.databindingwithrecyclerview.extension.makeToast
import com.harsh.databindingwithrecyclerview.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_main)*/

        val activityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val adapter = NewsAdapter(this)
        activityMainBinding.rvNews.adapter = adapter

        val newsViewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(NewsViewModel::class.java)
        if (isInternetAvailable())
            newsViewModel.getNewsHeadlines(Source.GOOGLE, getString(R.string.news_api_key))
        else makeToast("Please check device internet.")

        newsViewModel.newsLiveData.observe(this, Observer {
            when (it) {
                is APIResult.Success -> adapter.addData(it.data)
                is APIResult.Error -> makeToast(it.exception.message!!)
            }
        })

        adapter.onNewsUrlClick = {}
        adapter.onNewsClick = {}
    }
}