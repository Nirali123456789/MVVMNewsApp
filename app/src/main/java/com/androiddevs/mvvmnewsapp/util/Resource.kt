/**Created By Nirali Pandya
 * Date :2024-01-20
 * Time :5:34 p.m.
 * Project Name :MVVMNewsApp
 *
 */
package com.androiddevs.mvvmnewsapp.util

sealed  class Resource<T> (
    val data :T? =null,
    val message :String? = null

){
    class Success<T>(data: T?) :Resource<T>(data)
    class Error<T>(message: String?,data: T?=null) : Resource<T>(data,message)
    class Loading<T> : Resource<T>()
}