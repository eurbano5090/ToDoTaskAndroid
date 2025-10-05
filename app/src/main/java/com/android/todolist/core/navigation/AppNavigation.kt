package com.android.todolist.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.todolist.data.models.ToDoData
import com.android.todolist.data.models.ToDoData.findById
import com.android.todolist.ui.sreens.add.AddToDoScreen
import com.android.todolist.ui.sreens.home.ToDoDetailScreen
import com.android.todolist.ui.sreens.home.ToDoListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "toDoList"
    ) {
        composable("toDoList") {
            ToDoListScreen(navController = navController)
        }
        composable(
            route = "toDoDetail/{toDoItemId}",
            arguments = listOf(navArgument("toDoItemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("toDoItemId") ?: 0
            val todo = ToDoData.findById(todoId)
            todo?.let {
                ToDoDetailScreen(navController, it)
            }

        }
        composable("addToDo") {
            AddToDoScreen(navController = navController, toDo = ToDoData.toDoList[0])
        }
    }
}