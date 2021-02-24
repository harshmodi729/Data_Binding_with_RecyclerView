package com.harsh.databindingwithrecyclerview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harsh.databindingwithrecyclerview.data.APIResult
import com.harsh.databindingwithrecyclerview.data.ApiClient
import com.harsh.databindingwithrecyclerview.data.ApiInterface
import com.harsh.databindingwithrecyclerview.model.Article
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private var api: ApiInterface
    private val _newsLiveData = MutableLiveData<APIResult<ArrayList<Article>>>()
    val newsLiveData: LiveData<APIResult<ArrayList<Article>>>
        get() = _newsLiveData

    init {
        api = getApi()
    }

    /**
     * Get news headlines data from the server
     */
    fun getNewsHeadlines(source: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = api.getHeadLines(source, apiKey)
                /**
                 * Check response is correct or not
                 */
                if (response.isSuccess) {
                    /**
                     * Check response is not null if it is then return with  error message
                     */
                    response.data?.let {
                        _newsLiveData.value = APIResult.Success(it)
                    } ?: kotlin.run {
                        _newsLiveData.value =
                            APIResult.Error(IllegalStateException("Something went wrong."))
                    }
                } else {
                    _newsLiveData.value =
                        APIResult.Error(IllegalStateException("Something went wrong."))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _newsLiveData.value =
                    APIResult.Error(IllegalStateException("Something went wrong."))
            }
        }
    }

    /**
     * Get API request methods
     */
    private fun getApi(): ApiInterface {
        return ApiClient.getInstance().create(ApiInterface::class.java)
    }
}