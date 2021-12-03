package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.ui.apods_list.ApodsUi

interface ApodsDomainToUiMapper: Abstract.Mapper {
    fun map(apods: List<ApodDomain>): ApodsUi

    fun map(errorType: ErrorType): ApodsUi
}