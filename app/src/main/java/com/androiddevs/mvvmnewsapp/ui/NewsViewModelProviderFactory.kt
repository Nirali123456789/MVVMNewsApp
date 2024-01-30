/**Created By Nirali Pandya
 * Date :2024-01-20
 * Time :5:27 p.m.
 * Project Name :MVVMNewsApp
 *
 */
package com.androiddevs.mvvmnewsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.mvvmnewsapp.NewsApplication
import com.androiddevs.mvvmnewsapp.repository.NewsRepository

class NewsViewModelProviderFactory(
    val newsApplication: NewsApplication,
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(application = newsApplication, newsRepository = newsRepository) as T
    }
}