package com.bendix.novel.ui.square

import android.util.Log
import com.bendix.novel.model.ResponseSearchPageByKeyword
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.bendix.novel.model.BookBean
import com.bendix.novel.model.RequestBookInfo
import com.bendix.novel.model.RequestSearchPageByKeyword
import com.bendix.novel.model.ResponseBookInfo
import com.bendix.novel.repository.SquareRepository
import com.bendix.novel.ui.base.BaseViewModel
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SquareViewModel @AssistedInject constructor(
    private val squareRepository:SquareRepository
):BaseViewModel() {

    private val searchKeyWordFetching: MutableLiveData<RequestSearchPageByKeyword> = MutableLiveData()
    private val bookInfoFetching: MutableLiveData<RequestBookInfo> = MutableLiveData()
    val bookInfo: LiveData<ResponseBookInfo>

    private val _book: MutableLiveData<BookBean> = MutableLiveData()
    private val _currentPage: MutableLiveData<Int> = MutableLiveData()
    private val _totalPage: MutableLiveData<Int> = MutableLiveData()
    private val _keyWord: MutableLiveData<String> = MutableLiveData()
    private val _type: MutableLiveData<String> = MutableLiveData()
    private val _shopName: MutableLiveData<String> = MutableLiveData()
    val searchKeyWord: LiveData<ResponseSearchPageByKeyword>

    init {
        _currentPage.value=1
        _totalPage.value=1

        searchKeyWord = searchKeyWordFetching.switchMap {
            Log.d("zzl", searchKeyWordFetching.value?.keyword.toString())
            _isLoading.postValue(true)
            launchOnViewModelScope {
                squareRepository.fetchSearchKeyWord(
                    request = it,
                    onSuccess = {
                        _isLoading.postValue(false)
                    },
                    onError = {
                        _isLoading.postValue(false)
                        _toast.postValue(it)
                    }
                ).asLiveData()
            }
        }
        bookInfo=bookInfoFetching.switchMap {
            _isLoading.postValue(true)
            Log.d("bookInfo","bookInfo")
            launchOnViewModelScope {
                squareRepository.fetchBookInfo(
                    request = it,
                    onSuccess = {
                        _isLoading.postValue(false)
                    },
                    onError = {
                        _isLoading.postValue(false)
                        _toast.postValue(it)
                    }
                ).asLiveData()
            }
            
        }
    
    }
    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(): SquareViewModel
    }
    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create() as T
            }
        }
    }

    @MainThread
    fun fetchSearchKeyWord(keyword: String, page: Int){
        Log.d("zzl", keyword)
        Log.d("zzl", page.toString())
       val test =  RequestSearchPageByKeyword(keyword, page)
        Log.d("zzl", test.toString())
        searchKeyWordFetching.postValue(test)
        _currentPage.value = page
        if (keyword.isNotBlank()) _keyWord.value = keyword
    }

    @MainThread
    fun fetchShopName(shopName:String){
        _shopName.value=shopName
    }

    fun fetchPage(currentPage: Int, totalPage: Int) {
        _currentPage.value = currentPage
        _totalPage.value = totalPage
    }

    fun fetchBookInfo(url: String) {
        bookInfoFetching.value = RequestBookInfo(url)
    }

    fun fetchBook(book: BookBean) {
        _book.postValue(book)
    }
}