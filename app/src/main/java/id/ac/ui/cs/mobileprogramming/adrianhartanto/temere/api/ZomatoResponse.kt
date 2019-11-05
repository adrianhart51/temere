package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api

import com.google.gson.annotations.SerializedName


data class ZomatoResponse<T>(
    @SerializedName("categories")
    val categories: List<T>
)