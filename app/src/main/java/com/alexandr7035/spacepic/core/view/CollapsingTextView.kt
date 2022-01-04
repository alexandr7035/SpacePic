package com.alexandr7035.spacepic.core.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.alexandr7035.spacepic.core.extensions.debug
import timber.log.Timber

class CollapsingTextView(context: Context, attrs: AttributeSet): AppCompatTextView(context, attrs), View.OnClickListener {

    private var predefinedMaxLines = 0

    init {
        predefinedMaxLines = maxLines

        // If want to collapse on clicks directly on the view
        // Otherwise use toggle() method
        if (isClickable) {
            super.setOnClickListener(this)
        }
    }

    override fun onClick(p0: View?) {
        Timber.debug("click on textview")
        toggle()
    }

    fun toggle() {
        if (maxLines == predefinedMaxLines) {
            maxLines = Int.MAX_VALUE
        }
        else {
            maxLines = predefinedMaxLines
        }
    }
}