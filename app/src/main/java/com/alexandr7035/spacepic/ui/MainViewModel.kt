package com.alexandr7035.spacepic.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandr7035.spacepic.domain.ApodsDomainToUiMapper
import com.alexandr7035.spacepic.domain.ApodsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val interactor: ApodsInteractor, private val apodsDomainToUiMapper: ApodsDomainToUiMapper): ViewModel() {
    val apodsLiveData = MutableLiveData<ApodsUi>()

    fun init() {
        apodsLiveData.postValue(ApodsUi.Progress())

        viewModelScope.launch(Dispatchers.IO) {
            val apods = interactor.fetchApods(System.currentTimeMillis())
            val apodsUi = apods.map(apodsDomainToUiMapper)

            withContext(Dispatchers.Main) {
                apodsLiveData.value = apodsUi
            }
        }
    }

    fun loadMore(lastApodDate: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val apods = interactor.fetchApods(lastApodDate)
            val apodsUi = apods.map(apodsDomainToUiMapper)

            withContext(Dispatchers.Main) {
                apodsLiveData.value = apodsUi
            }
        }
    }
}