package com.android.todolist.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.todolist.ui.sreens.add.AddToDoScreen
import com.android.todolist.ui.sreens.done.DoneToDoScreen
import com.android.todolist.ui.sreens.home.ToDoDetailScreen
import com.android.todolist.ui.sreens.home.ToDoListScreen
import com.android.todolist.viewModel.home.ToDoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ToDoViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "toDoList"
    ) {
        composable("toDoList") {
            ToDoListScreen(
                viewModel = viewModel,
                navController = navController)
        }
        composable("doneToDos") {  // <- NUEVA RUTA
            DoneToDoScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(
            route = "toDoDetail/{toDoItemId}",
            arguments = listOf(navArgument("toDoItemId") { type = NavType.StringType })  // <- STRING no INT
        ) { backStackEntry ->
            val toDoItemId = backStackEntry.arguments?.getString("toDoItemId") ?: ""  // <- Así se obtiene
            ToDoDetailScreen(
                toDoItemId = toDoItemId,
                viewModel = viewModel,
                navController = navController
            )
        }


        composable("addToDo") {
            AddToDoScreen(
                navController = navController,
                toDo = null,
                viewModel = viewModel)
        }
    }
}