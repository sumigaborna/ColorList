package com.sumigaborna.colorlist.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sumigaborna.colorlist.database.ColorItem
import com.sumigaborna.colorlist.repository.ColorsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ColorsViewModel(application: Application, private val repo: ColorsRepository) :
    AndroidViewModel(application) {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("TAG", "Error in ColorsViewModel: ${exception.message}")
    }

    val databaseInitalize = MutableLiveData<Boolean>(false)
    val color = MutableLiveData<ColorItem>()

    val colors = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 20 * 3
        )
    ) {
        repo.getColors()
    }.flow
        .cachedIn(viewModelScope)

    fun addColors() {
        viewModelScope.launch(handler) {
            val dbInit = repo.addColors()
            databaseInitalize.postValue(dbInit)
        }
    }

    fun getColorById(colorId: Int) {
        viewModelScope.launch(handler) {
            withContext(Dispatchers.Default) {
                val databaseColor = repo.getColorById(colorId)
                color.postValue(databaseColor)
            }
        }
    }
}