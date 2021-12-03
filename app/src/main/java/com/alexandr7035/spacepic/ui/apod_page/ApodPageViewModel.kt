package com.alexandr7035.spacepic.ui.apod_page

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexandr7035.spacepic.domain.ApodDomainToUiMapper
import com.alexandr7035.spacepic.domain.ApodsInteractor
import com.alexandr7035.spacepic.ui.ApodUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ApodPageViewModel @Inject constructor(
    private val interactor: ApodsInteractor,
    private val apodDomainToUiMapper: ApodDomainToUiMapper
): ViewModel() {

    val singleApodLiveData = MutableLiveData<ApodUi>()

    fun init(date: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val apod = interactor.fetchSingleApod(date)
            val apodUi = apod.map(apodDomainToUiMapper)

            withContext(Dispatchers.Main) {
                singleApodLiveData.value = apodUi
            }
        }
    }
}