package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.restaurant.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.Collections.emptyList

data class RestaurantInfo(
    var restaurant: Restaurant
)

@Entity(tableName = "restaurant")
data class Restaurant(
    @PrimaryKey
    @field:SerializedName("id")
    var id: String,
    @field:SerializedName("url")
    var url: String,
    @field:SerializedName("cuisines")
    var cuisines: String,
    @field:SerializedName("name")
    var name: String,
    @field:SerializedName("average_cost_for_two")
    var average_cost_for_two: Int,
    @field:SerializedName("currency")
    var currency: String,
    @field:SerializedName("featured_image")
    var featured_image: String,
    @field:SerializedName("menu_url")
    var menu_url: String,
    @field:SerializedName("price_range")
    var price_range: Int,
    @Ignore
    var R: R?,
    @Ignore
    var all_reviews: AllReviews?,
    var all_reviews_count: Int,
    var apikey: String,
    var book_again_url: String,
    var book_form_web_view_url: String,
    var deeplink: String,
    @Ignore
    var establishment: List<String>,
    @Ignore
    var establishment_types: List<Any>,
    var events_url: String,
    var has_online_delivery: Int,
    var has_table_booking: Int,
    @Ignore
    var highlights: List<String>,
    var include_bogo_offers: Boolean,
    @field:SerializedName("flag_book_form_web_view")
    var flag_book_form_web_view: Int,
    @field:SerializedName("flag_delivering_now")
    var flag_delivering_now: Int,
    @field:SerializedName("flag_table_reservation_supported")
    var flag_table_reservation_supported: Int,
    @field:SerializedName("flag_zomato_book_res")
    var flag_zomato_book_res: Int,
    @Ignore
    var location: Location?,
    var mezzo_provider: String,
    @Ignore
    var offers: List<Any>,
    var opentable_support: Int,
    var phone_numbers: String,
    var photo_count: Int,
    @Ignore
    var photos: List<PhotoInfo>,
    var photos_url: String,
    var switch_to_order_menu: Int,
    var thumb: String,
    var timings: String,
    @Ignore
    var user_rating: UserRating?
) {
    constructor() : this("", "", "", "", 0, "", "", "",
        0, null, null, 0, "", "", "", "",
        emptyList(), emptyList(), "", 0, 0, emptyList(), false,
        0, 0, 0, 0, null, "", emptyList(),
        0, "", 0, emptyList(), "", 0, "", "", null)
}

data class R(
    var has_menu_status: HasMenuStatus,
    var res_id: Int
)

data class AllReviews(
    var reviews: List<Any>
)

data class Title(
    var text: String
)

data class BgColor(
    var tint: String,
    var type: String
)

data class Photo(
    var caption: String,
    var friendly_time: String,
    var height: Int,
    var id: String,
    var res_id: Int,
    var thumb_url: String,
    var timestamp: Int,
    var url: String,
    var user: User,
    var width: Int
)

data class User(
    var foodie_color: String,
    var foodie_level: String,
    var foodie_level_num: Int,
    var name: String,
    var profile_deeplink: String,
    var profile_image: String,
    var profile_url: String
)

data class Location(
    var address: String,
    var city: String,
    var city_id: Int,
    var country_id: Int,
    var latitude: String,
    var locality: String,
    var locality_verbose: String,
    var longitude: String,
    var zipcode: String
)

data class HasMenuStatus(
    var delivery: Int,
    var takeaway: Int
)

data class PhotoInfo(
    var photo: Photo
)

data class UserRating(
    var aggregate_rating: Double,
    var rating_color: String,
    var rating_obj: RatingObj,
    var rating_text: String,
    var votes: Int
)

data class RatingObj(
    var bg_color: BgColor,
    var title: Title
)