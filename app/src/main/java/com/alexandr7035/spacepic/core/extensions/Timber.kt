package com.alexandr7035.spacepic.core.extensions

import timber.log.Timber

fun Timber.Forest.debug(message: String) {
    tag("DEBUG_TAG").d(message)
}