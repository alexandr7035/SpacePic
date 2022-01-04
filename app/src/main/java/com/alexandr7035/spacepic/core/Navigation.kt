package com.alexandr7035.spacepic.core

import androidx.fragment.app.Fragment

interface Navigation {
    fun navigateStart()

    fun add(fragment: Fragment, addToBackStack : Boolean = false)

    fun replace(fragment: Fragment, addToBackStack : Boolean = false)

    fun navigateBack()

    fun checkIfBackStackNotEmpty(): Boolean
}