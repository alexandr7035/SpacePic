package com.alexandr7035.spacepic.ui

import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.domain.ApodDomain

abstract class ApodsUi {
    class Success(apods: List<ApodUi>): ApodsUi()

    class Fail(errorMessage: String): ApodsUi()
}