package com.android.todolist.ui.sreens.home

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.todolist.R
import com.android.todolist.data.models.Categoria
import com.android.todolist.data.models.ToDoItem
import com.android.todolist.ui.theme.MyGradient
import com.android.todolist.ui.theme.Purple40
import com.android.todolist.viewModel.home.ToDoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoDetailScreen(
    navController: NavController,
    toDoItemId: String,
    viewModel: ToDoViewModel,
    modifier: Modifier = Modifier
) {
    var todo by remember { mutableStateOf<ToDoItem?>(null) }
    var cat by remember { mutableStateOf<Categoria?>(null) }
    var title by rememberSaveable  { mutableStateOf("") }
    var description by rememberSaveable  { mutableStateOf("") }
    var categoriaId by rememberSaveable  { mutableStateOf("") }
    val context = LocalContext.current


    LaunchedEffect(toDoItemId,categoriaId) {
        val resultado = viewModel.obtenerPorId(toDoItemId)
        val catResult = viewModel.buscarCategoriaPorId(categoriaId)

        resultado?.let {
            todo = it
            title = it.title
            description = it.description ?: ""
            categoriaId = it.categoriaId
        }
        cat = catResult as? Categoria
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40
                ),
                title = { Text("Detalle de Tarea") },
                actions = {
                IconButton(onClick = {
                    navController.navigate("editToDo/$toDoItemId")
                }) {
                    Icon(
                        painter = painterResource(R.drawable.edit),
                        contentDescription = "Editar tarea",


                    )}
                    todo?.let {
                        IconButton(onClick = {
                            shareTask(context, todo?.title?: "", todo?.description ?: "")
                        }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_share),
                                contentDescription = "Compartir tarea",

                                )
                        }
                    }

                },

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
        if (todo != null) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(MyGradient)
                    .padding(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 9.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .size(80.dp)
                            .padding(12.dp)
                            .clip(CircleShape),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = todo?.title?.first()?.uppercase() ?: "",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                         /*   Icon(
                                painter = painterResource(cat?.iconRes?:R.drawable.ic_other),
                                contentDescription = cat?.nombre,
                                modifier = Modifier.size(40.dp),
                            )*/
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = todo?.title?: "",
                        style = MaterialTheme.typography.headlineMedium,  // <- Más grande
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    todo?.description?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyLarge,
                            lineHeight = 24.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Fecha: ${todo?.date}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "🕒 ${todo?.time}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Tarea no encontrada")
            }
        }
    }}

    fun shareTask(context: Context, title: String, description: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "📋 *Tarea:* $title\n📝 Descripción: $description"
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Compartir tarea con...")
        context.startActivity(shareIntent)
    }
