package com.stenopolz.weatherapp

import com.stenopolz.weatherapp.model.data.application.ResourceProvider

class TestResourceProvider : ResourceProvider {

    override fun getString(stringId: Int): String = "String $stringId"

    override fun getString(stringId: Int, vararg formatArgs: Any): String {
        val stringBuilder = StringBuilder()
        formatArgs.forEach {
            stringBuilder.append(it.toString())
        }
        return "String $stringId with arguments $stringBuilder"
    }
}
