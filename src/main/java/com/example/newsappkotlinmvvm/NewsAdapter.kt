package com.example.newsappkotlinmvvm

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsappkotlinmvvm.databinding.ItemViewBinding

class NewsAdapter(val context: Context, val articles: List<Article>): RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        //val inflater = LayoutInflater.from(context)
        //val view = inflater.inflate(R.layout.item_view, parent, false)
        val view  = ItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        //Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        //holder.newsTitle.text = article.title
        //holder.newsDescription.text = article.description

        holder.apply {
            bind(article)
        }

        holder.itemView.setOnClickListener{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("URL", article.url)
            context.startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("loadImage")
        fun loadImage(thumbs: ImageView, url: String) {
            Glide.with(thumbs)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(thumbs)
        }
    }

    class ArticleViewHolder(val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        //var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        //var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        //var newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)

        fun bind(article:Article){
            binding.article = article
        }

    }

}