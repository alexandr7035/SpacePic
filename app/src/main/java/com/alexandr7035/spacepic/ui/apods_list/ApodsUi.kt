package com.alexandr7035.spacepic.ui.apods_list

import com.alexandr7035.spacepic.ui.ApodUi

abstract class ApodsUi() {
    class Success(val apods: List<ApodUi>): ApodsUi()

    class Progress(): ApodsUi()

    class Fail(val errorMessage: String): ApodsUi()
}
