package com.stenopolz.weatherapp.model.data.application

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes stringId: Int): String

    fun getString(@StringRes stringId: Int, vararg formatArgs: Any): String

    class DefaultResourceProvider(
        context: Context
    ) : ResourceProvider {

        private val res = context.resources

        override fun getString(@StringRes stringId: Int) = res.getString(stringId)

        override fun getString(@StringRes stringId: Int, vararg formatArgs: Any): String =
            res.getString(stringId, *formatArgs)
    }
}
