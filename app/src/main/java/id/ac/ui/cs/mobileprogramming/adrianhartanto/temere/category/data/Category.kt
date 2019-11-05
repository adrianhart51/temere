package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.category.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category")
data class Category(
    @PrimaryKey
    @field:SerializedName("id")
    val id : Int,
    @field:SerializedName("name")
    val name : String) {
}