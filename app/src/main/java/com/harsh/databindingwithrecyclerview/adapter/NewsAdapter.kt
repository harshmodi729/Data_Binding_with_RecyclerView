package com.harsh.databindingwithrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsh.databindingwithrecyclerview.databinding.LayNewsSummaryBinding
import com.harsh.databindingwithrecyclerview.model.Article

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var alNews = ArrayList<Article>()
    var onNewsUrlClick: ((newsUrl: String) -> Unit)? = null
    var onNewsClick: ((item: Article) -> Unit)? = null

    class ViewHolder(itemView: LayNewsSummaryBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
        val cvNews = itemView.cvNews
        val tvNewsUrl = itemView.tvNewsUrl
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayNewsSummaryBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = alNews[position]

        holder.binding.news = item
        holder.tvNewsUrl.setOnClickListener {
            onNewsUrlClick?.invoke(item.url)
        }
        holder.cvNews.setOnClickListener {
            onNewsClick?.invoke(item)
        }
    }

    override fun getItemCount() = alNews.size

    fun addData(alNews: ArrayList<Article>) {
        this.alNews = alNews
        notifyDataSetChanged()
    }
}