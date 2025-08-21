package silvia.test.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import silvia.test.todoapp.data.Todo
import silvia.test.todoapp.repository.TodoRepository

data class UiState(
    val isLoading: Boolean = false,
    val todos: List<Todo> = emptyList(),
    val selected: Todo? = null,
    val error: String? = null
)

class TodoViewModel(
    private val repo: TodoRepository = TodoRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(UiState(isLoading = true))
    val state: StateFlow<UiState> = _state

    init { loadTodos() }

    fun loadTodos() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repo.fetchTodos()
                .onSuccess { list ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        todos = list.sortedBy { it.id }
                    )
                }
                .onFailure {
                    _state.value = _state.value.copy(isLoading = false, error = it.message)
                }
        }
    }

    fun select(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            repo.fetchTodoDetail(id)
                .onSuccess { todo ->
                    _state.value = _state.value.copy(isLoading = false, selected = todo)
                }
                .onFailure {
                    _state.value = _state.value.copy(isLoading = false, error = it.message)
                }
        }
    }

    fun clearSelection() {
        _state.value = _state.value.copy(selected = null)
    }
}