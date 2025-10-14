package com.android.todolist.ui.sreens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.todolist.viewModel.auth.LoginViewModel
import com.android.todolist.R
import com.android.todolist.ui.theme.Amber400
import com.android.todolist.ui.theme.Slate200

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel ,
    navController: NavController
) {
    val context = LocalContext.current
    Scaffold { padding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
        modifier = Modifier.size(96.dp),
        painter = painterResource(R.drawable.logo),
        contentDescription = "Logo de la App"
       )

        Text(text = "Bienvenida", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {

                loginViewModel.showBiometricPrompt(
                    context = context,
                    onSuccess = {
                        Toast.makeText(context, "Autenticación exitosa", Toast.LENGTH_SHORT).show()
                        navController.navigate("toDoList")
                    },
                    onError = { error ->
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                )
            },
            modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                disabledContainerColor = Amber400,
                disabledContentColor = Slate200
            )
        ) {
            Text("Iniciar sesión con huella")
        }
    }
}}

