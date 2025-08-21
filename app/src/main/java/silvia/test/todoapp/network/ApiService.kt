package silvia.test.todoapp.network

import retrofit2.http.GET
import retrofit2.http.Path
import silvia.test.todoapp.data.Todo

interface ApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @GET("todos/{id}")
    suspend fun getTodoDetail(@Path("id") id: Int): Todo
}