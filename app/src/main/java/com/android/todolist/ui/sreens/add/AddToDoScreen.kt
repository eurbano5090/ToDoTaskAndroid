package com.android.todolist.ui.sreens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.android.todolist.data.models.ToDoItem
import com.android.todolist.viewModel.home.ToDoViewModel
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDoScreen(
    toDo: ToDoItem?,
    viewModel: ToDoViewModel = viewModel(),
    navController: NavController,
    modifier: Modifier = Modifier) {

    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        val date = remember { java.time.LocalDate.now().toString() }

    Scaffold(
    topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            title = { Text("Agregar tarea") },
        )}
            ) { padding ->
       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(padding)
               .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título de la tarea") },
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción de la tarea") },
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {

                val newId = UUID.randomUUID().toString()
                val newToDo = ToDoItem(
                    id = newId,
                    title = title,
                    description = description,
                    date = date,
                    isDone = false
                )

                viewModel.addToDo(newToDo)
                viewModel.saveToDos(context)
                navController.popBackStack()
            }) {
                Text("Agregar tarea")
            }
        }
    }}

