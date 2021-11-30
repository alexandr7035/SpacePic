package com.alexandr7035.spacepic.ui

abstract class ApodsUi() {
    class Success(val apods: List<ApodUi>): ApodsUi()

    class Progress(): ApodsUi()

    class Fail(val errorMessage: String): ApodsUi()
}
