package com.example.android.mynewsapp.allFragments.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.android.mynewsapp.allFragments.repo.RepoInterface
import com.example.android.mynewsapp.allFragments.dataLayer.DomainObject
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MyViewModel(private val repo: RepoInterface): ViewModel() {

    //Livedata object used to get all the items to be displayed by the app from the database
    val allObjects: LiveData<List<DomainObject>> = repo.getArticlesFromDB()

    //Variable used to navigate to the detail screen
    private val _navigateToSingleArticle = MutableLiveData<DomainObject?>()
    val navigateToSingleArticle: LiveData<DomainObject?>
    get() = _navigateToSingleArticle

    //When we start the viewModel class the first thing we do is fetch the data from the network
    init {
        refreshData()
    }

   fun refreshData(){
        viewModelScope.launch {
            try {
                repo.setDataFromNetwork()
            } catch (e: HttpException){
                Log.i("EXCEPTION",e.message())
            }

        }
    }

    fun onNavigateToSingleArticleComplete(){
        _navigateToSingleArticle.value = null
    }

    fun onNavigateToSingleArticleStarted(article: DomainObject){
        _navigateToSingleArticle.value = article
    }

}

@Suppress("UNCHECKED_CAST")
class Factory(private val repo: RepoInterface): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)){
            return MyViewModel(repo) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel")
    }
}