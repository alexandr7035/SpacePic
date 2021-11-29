package com.alexandr7035.spacepic.ui

import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.domain.ApodDomain

abstract class ApodsUi {
    class Success(val apods: List<ApodUi>): ApodsUi()

    class Fail(val errorMessage: String): ApodsUi()
}