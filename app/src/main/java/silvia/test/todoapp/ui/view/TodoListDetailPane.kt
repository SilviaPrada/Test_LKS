package silvia.test.todoapp.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import silvia.test.todoapp.data.Todo

@Composable
fun TodoDetailPane(
    todo: Todo?,
    onBack: () -> Unit
) {
    if (todo == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Pilih item untuk melihat detail")
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            FilledIconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null) }
            Spacer(Modifier.width(8.dp))
            Text("Detail Todo", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        }

        ElevatedCard(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                KeyValue("ID", "#${todo.id}")
                KeyValue("User", todo.userId.toString())
                KeyValue("Title", todo.title)
                KeyValue("Status", if (todo.completed) "Completed ✅" else "Pending ⏳")
            }
        }

        Text(
            "Catatan: Data bersumber dari JSONPlaceholder (read-only). " +
                    "Contoh ini menekankan arsitektur MVVM + Compose + responsive layout."
        )
    }
}

@Composable
private fun KeyValue(key: String, value: String) {
    Column {
        Text(key, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
        Text(value, style = MaterialTheme.typography.titleMedium)
    }
}