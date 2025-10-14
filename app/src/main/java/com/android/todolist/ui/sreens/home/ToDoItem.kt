package com.android.todolist.ui.sreens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.todolist.R
import com.android.todolist.data.models.ToDoItem
import com.android.todolist.ui.theme.MyGradient
import com.android.todolist.ui.theme.Purple40

@Composable
fun ToDoItem(
    toDoItem: ToDoItem,
    toggleDone: (String) -> Unit,
    removeToDo: (String) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { navController.navigate("toDoDetail/${toDoItem.id}") },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MyGradient)
                .clip(CircleShape)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = toDoItem.isDone,
                onCheckedChange = { toggleDone(toDoItem.id) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = toDoItem.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = if (toDoItem.isDone) FontWeight.Normal else FontWeight.Bold,
                    color = if (toDoItem.isDone)
                        MaterialTheme.colorScheme.outline
                    else
                        Purple40
                )
            }


            IconButton(onClick = { removeToDo(toDoItem.id) }) {
                Icon(
                    painter = painterResource(R.drawable.ic_delete),
                    contentDescription = "Eliminar tarea",
                    tint = Purple40
                )
            }
        }
    }
}
