package com.harsh.databindingwithrecyclerview.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.harsh.databindingwithrecyclerview.R
import com.harsh.databindingwithrecyclerview.base.Constants
import com.harsh.databindingwithrecyclerview.databinding.ActivityNewsDetailBinding
import com.harsh.databindingwithrecyclerview.model.Article

class NewsDetailActivity : AppCompatActivity() {

    private var item: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_news_detail)*/

        val binding = DataBindingUtil.setContentView<ActivityNewsDetailBinding>(
            this,
            R.layout.activity_news_detail
        )

        intent.extras?.let {
            item = intent.getSerializableExtra(Constants.NEWS_DATA) as Article
            item?.let { article ->
                title = article.author
                binding.newsDetail = article
                binding.ivNewsTitle.setOnClickListener {
                    startActivity(
                        Intent(this, ImageViewActivity::class.java)
                            .putExtra(Constants.IMAGE_URL, article.urlToImage)
                    )
                }
            }
        }
    }
}