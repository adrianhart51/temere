package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.api

import com.google.gson.annotations.SerializedName
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data.Categories


data class CategoriesResponse(
    @SerializedName("categories")
    val categories: List<Categories>
)