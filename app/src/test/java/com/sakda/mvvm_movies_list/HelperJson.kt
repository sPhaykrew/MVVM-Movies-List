package com.sakda.mvvm_movies_list

import java.io.InputStreamReader

object HelperJson {

    fun readFileResource(filename : String) : String {
        val inputStream = HelperJson::class.java.getResourceAsStream(filename)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream,"UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
 }