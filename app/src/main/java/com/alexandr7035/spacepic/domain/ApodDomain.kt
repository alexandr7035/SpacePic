package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.ui.ApodUi

abstract class ApodDomain(): Abstract.Object<ApodUi, ApodDomainToUiMapper>() {
    class ImageApod(
        private val title: String,
        private val apodUri: String,
        private val date: String,
        private val description: String
    ): ApodDomain() {

        override fun map(mapper: ApodDomainToUiMapper): ApodUi {
            return mapper.map()
        }
    }

    class VideoApod(

    ): ApodDomain() {
        override fun map(mapper: ApodDomainToUiMapper): ApodUi {
            return mapper.map()
        }
    }
}