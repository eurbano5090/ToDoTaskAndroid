package com.android.todolist.ui.sreens.add
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.todolist.R
import com.android.todolist.data.models.ToDoItem
import com.android.todolist.ui.theme.Purple40
import com.android.todolist.viewModel.home.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditToDoScreen (
    navController: NavController,
    id: String,
    viewModel: ToDoViewModel,
    modifier: Modifier = Modifier){

  //  val context = LocalContext.current
    var todo by remember { mutableStateOf<ToDoItem?>(null) }
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var categoriaId by remember { mutableStateOf("") }

    LaunchedEffect(id) {
        val resultado = viewModel.obtenerPorId(id)
        todo = resultado
        resultado?.let {
            title = it.title
            description = it.description ?: ""
            categoriaId = it.categoriaId
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40
                ),
                title = { Text("Editar tarea") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = "Volver"
                        )
                    }
                }
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

                val editToDo = ToDoItem(
                    id = id,
                    title = title,
                    description = description,
                    date = todo?.date ?: java.time.LocalDate.now().toString(),
                    isDone = todo?.isDone?:false,
                    time = todo?.time?: java.time.LocalTime.now().toString(),
                    categoriaId = categoriaId
                )

                viewModel.actualizarToDo(editToDo)
                navController.popBackStack()
            }) {
                Text("Guardar cambios")
            }
        }
    }}
