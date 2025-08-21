package silvia.test.todoapp.data


import com.squareup.moshi.Json

data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    @Json(name = "completed") val completed: Boolean
)