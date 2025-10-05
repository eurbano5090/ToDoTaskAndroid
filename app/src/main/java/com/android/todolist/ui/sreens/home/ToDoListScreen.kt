package com.android.todolist.ui.sreens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.android.todolist.viewModel.home.ToDoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen (viewModel: ToDoViewModel ,
                    navController: NavController)
    {
        val context = LocalContext.current

        // Carga los datos cuando se crea el Composable
        LaunchedEffect(Unit) {
            viewModel.loadToDos(context)
        }
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    ),
                    title = { Text("Lista de Pendientes") },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate("addToDo")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Agregar tarea",
                                tint = MaterialTheme.colorScheme.onTertiary
                            )
                        }
                    }
                )
            }
        )  { padding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                items(viewModel.toDoList.size) { todo ->
                    ToDoItem(toDoItem = viewModel.toDoList[todo],
                        navController = navController,
                        toggleDone = { id -> viewModel.toggleDone(id.toString()) },
                        removeToDo = { id -> viewModel.removeToDo(id.toString()) })
                }
            }

        }
    }
