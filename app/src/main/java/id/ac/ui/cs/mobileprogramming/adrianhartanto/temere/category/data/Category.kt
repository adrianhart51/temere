package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    @field:SerializedName("category_id")
    val category_id : Int,
    @field:SerializedName("category_name")
    val category_name : String) {
}