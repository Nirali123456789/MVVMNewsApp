/**Created By Nirali Pandya
 * Date :2024-01-20
 * Time :5:03 p.m.
 * Project Name :MVVMNewsApp
 *
 */
package com.androiddevs.mvvmnewsapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.models.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_article_preview.view.ivArticleImage
import kotlinx.android.synthetic.main.item_article_preview.view.tvDescription
import kotlinx.android.synthetic.main.item_article_preview.view.tvPublishedAt
import kotlinx.android.synthetic.main.item_article_preview.view.tvSource
import kotlinx.android.synthetic.main.item_article_preview.view.tvTitle

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    val TAG= "NewsAdapter"

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Log.d(TAG, "onBindViewHolder: ${article.urlToImage}")
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            setOnClickListener {
                Log.d(TAG, "onBindViewHolder: $it")
                onItemClickListner?.let { it(article) }
            }
        }
    }

    private var onItemClickListner: ((Article) -> Unit)? = null

    fun setOnItemClickListner(listner: (Article) -> Unit) {
        onItemClickListner = listner
    }
}