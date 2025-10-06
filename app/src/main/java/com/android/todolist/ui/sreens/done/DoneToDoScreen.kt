package com.android.todolist.ui.sreens.done

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.todolist.ui.sreens.home.ToDoItem
import com.android.todolist.ui.theme.Purple40
import com.android.todolist.viewModel.home.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoneToDoScreen(
    viewModel: ToDoViewModel,
    navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40
                ),
                title = { Text("Tareas Completadas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    )
    { padding ->
        if (viewModel.doneToDos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🎉",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No hay tareas completadas aún",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(viewModel.doneToDos.size) { index ->
                    ToDoItem(
                        toDoItem = viewModel.doneToDos[index],
                        navController = navController,
                        toggleDone = { id ->
                            viewModel.toggleDone(id)
                            viewModel.saveToDos(context)
                        },
                        removeToDo = { id ->
                            viewModel.removeToDo(id)
                            viewModel.saveToDos(context)
                        }
                    )
                }
            }
        }
    }
}


