package silvia.test.todoapp.repository

import silvia.test.todoapp.data.Todo
import silvia.test.todoapp.network.RetrofitClient

class TodoRepository {
    private val api = RetrofitClient.api

    suspend fun fetchTodos(): Result<List<Todo>> =
        runCatching { api.getTodos() }

    suspend fun fetchTodoDetail(id: Int): Result<Todo> =
        runCatching { api.getTodoDetail(id) }
}