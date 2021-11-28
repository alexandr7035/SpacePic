package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.data.ApodData
import com.alexandr7035.spacepic.ui.ApodsUi
import java.lang.Exception

abstract class ApodsDomain: Abstract.Object<ApodsUi, ApodsDomainToUiMapper>() {
    class Success(apods: List<ApodData>): ApodsDomain() {
        override fun map(mapper: ApodsDomainToUiMapper): ApodsUi {
            TODO("Not yet implemented")
        }

    }

    class Fail(e: Exception): ApodsDomain() {
        override fun map(mapper: ApodsDomainToUiMapper): ApodsUi {
            TODO("Not yet implemented")
        }

    }
}