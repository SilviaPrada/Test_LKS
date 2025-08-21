package silvia.test.todoapp.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import silvia.test.todoapp.ui.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(
    widthSizeClass: WindowWidthSizeClass,
    vm: TodoViewModel = viewModel()
) {
    val state by vm.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Todos – Silvia") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = "Note Icon",
                        tint = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6650a4), // background AppBar
                    titleContentColor = Color.White,    // warna teks title
                    navigationIconContentColor = Color.White, // kalau ada icon
                    actionIconContentColor = Color.White     // kalau ada action icon
                )
            )
        }
    ) { inner ->
        val modifier = Modifier.padding(inner)
        if (widthSizeClass >= WindowWidthSizeClass.Medium) {
            // Tablet: dua panel
            Row(modifier.fillMaxSize()) {
                Surface(tonalElevation = 2.dp, modifier = Modifier.width(380.dp).fillMaxHeight()) {
                    TodoListPane(
                        state = state,
                        onRefresh = { vm.loadTodos() },
                        onClick = { vm.select(it) }
                    )
                }
                Divider(Modifier.width(1.dp).fillMaxHeight())
                Box(Modifier.fillMaxSize()) {
                    TodoDetailPane(
                        todo = state.selected,
                        onBack = { vm.clearSelection() }
                    )
                }
            }
        } else {
            // Phone: navigasi
            val nav = rememberNavController()
            NavHost(
                navController = nav,
                startDestination = "list",
                modifier = modifier.fillMaxSize()
            ) {
                composable("list") {
                    TodoListPane(
                        state = state,
                        onRefresh = { vm.loadTodos() },
                        onClick = { id ->
                            vm.select(id)
                            nav.navigate("detail/$id")
                        }
                    )
                }
                composable(
                    "detail/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) {
                    TodoDetailPane(
                        todo = state.selected,
                        onBack = { nav.popBackStack() }
                    )
                }
            }
        }
    }
}