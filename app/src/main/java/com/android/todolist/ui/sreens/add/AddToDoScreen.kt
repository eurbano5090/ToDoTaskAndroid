package com.android.todolist.ui.sreens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.android.todolist.R
import com.android.todolist.data.models.CategoriasPredefinidas
import com.android.todolist.data.models.ToDoItem
import com.android.todolist.ui.components.Selectors
import com.android.todolist.ui.theme.Purple40
import com.android.todolist.viewModel.home.ToDoViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToDoScreen(
    toDo: ToDoItem?,
    viewModel: ToDoViewModel = viewModel(),
    navController: NavController,
    modifier: Modifier = Modifier) {

    //   val context = LocalContext.current
    val currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    val date = rememberSaveable { java.time.LocalDate.now().toString() }
    var selectedCategoriaId by rememberSaveable { mutableStateOf(CategoriasPredefinidas.lista.first().id) }



    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40
                ),
                title = { Text("Agregar tarea") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 15.dp)
                .padding(top = 15.dp)
        ) {
            Text(
                text = "Tipo de Tarea :",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Selectors(
                categorias = CategoriasPredefinidas.lista,
                selectedCategoriaId = selectedCategoriaId,
                onCategoriaSelected = { selectedCategoriaId = it }
            )
            Text(
                text = "Fecha: ${date}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 15.dp)
                    .padding(horizontal = 15.dp),
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
                        isDone = false,
                        categoriaId = selectedCategoriaId,
                        time = currentTime
                    )


                    viewModel.insertarToDo(newToDo)
                    navController.popBackStack()
                }) {
                    Text("Agregar tarea")
                }
            }
        }
    }
}
