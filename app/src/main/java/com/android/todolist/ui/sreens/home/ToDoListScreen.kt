package com.android.todolist.ui.sreens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.todolist.R
import com.android.todolist.ui.theme.Purple40
import com.android.todolist.viewModel.home.ToDoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(
    viewModel: ToDoViewModel,
    navController: NavController
) {
    val pendingTasks by viewModel.pendingToDos.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40
                ),
                title = { Text("Lista de Pendientes") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("doneToDos")
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = "Ver completadas",
                        )
                    }
                    IconButton(onClick = {
                        navController.navigate("addToDo")
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = "Agregar tarea",
                        )
                    }
                }
            )
        }
    ) { padding ->
        if (pendingTasks.isEmpty()) {
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
                        text = "📝",
                        style = MaterialTheme.typography.displayLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No hay tareas pendientes",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Presiona + para agregar una nueva tarea",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = pendingTasks,
                    key = { it.id }
                ) { todo ->
                    ToDoItemCard(
                        toDoItem = todo,
                        navController = navController,
                        toggleDone = {
                            viewModel.doneToDos(todo.id)
                        },
                        removeToDo = {
                            viewModel.removeToDo(todo.id)
                        }
                    )
                }
            }
        }
    }
}