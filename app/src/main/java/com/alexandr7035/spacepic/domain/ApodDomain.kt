package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.ui.ApodUi

abstract class ApodDomain(): Abstract.Object<ApodUi, ApodDomainToUiMapper>() {
    class ImageApod(
        private val title: String,
        private val imageUrl: String,
        private val date: String,
        private val description: String
    ): ApodDomain() {

        override fun map(mapper: ApodDomainToUiMapper): ApodUi {
            return mapper.mapImage(title, imageUrl, date, description)
        }
    }

    class VideoApod(
        private val title: String,
        private val videoThumbUrl: String,
        private val date: String,
        private val description: String
    ): ApodDomain() {
        override fun map(mapper: ApodDomainToUiMapper): ApodUi {
            return mapper.mapVideo(title, videoThumbUrl, date, description)
        }
    }
}