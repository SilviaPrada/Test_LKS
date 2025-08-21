package silvia.test.todoapp.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import silvia.test.todoapp.data.Todo
import silvia.test.todoapp.ui.viewmodel.UiState

@Composable
fun TodoListPane(
    state: UiState,
    onRefresh: () -> Unit,
    onClick: (id: Int) -> Unit
) {
    when {
        state.isLoading && state.todos.isEmpty() -> ListLoading()
        state.error != null -> ErrorState(message = state.error!!, onRetry = onRefresh)
        else -> TodoList(list = state.todos, onClick = onClick, refreshing = state.isLoading)
    }
}

@Composable
private fun TodoList(
    list: List<Todo>,
    onClick: (Int) -> Unit,
    refreshing: Boolean
) {
    Column(Modifier.fillMaxSize()) {
        if (refreshing) LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(list) { item ->
                ElevatedCard(
                    onClick = { onClick(item.id) }
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(
                                "#${item.id} ${item.title}",
                                style = MaterialTheme.typography.titleMedium,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(4.dp))
                            AssistChip( onClick = { onClick(item.id) }, label = { Text(if (item.completed) "Completed" else "Pending") }, )
                        }
                        Checkbox(
                            checked = item.completed,
                            onCheckedChange = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ListLoading() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(message: String, onRetry: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Terjadi kesalahan:\n$message")
            Spacer(Modifier.height(8.dp))
            Button(onClick = onRetry) { Text("Coba Lagi") }
        }
    }
}