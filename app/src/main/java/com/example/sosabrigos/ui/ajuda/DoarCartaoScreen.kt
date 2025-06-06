
package com.example.sosabrigos.ui.ajuda

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sosabrigos.ui.home.gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoarCartaoScreen(navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var numeroCartao by remember { mutableStateOf("") }
    var validade by remember { mutableStateOf("") }
    var codigoCV by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Doação com Cartão", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF102E50),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome no Cartão") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = numeroCartao,
                onValueChange = { numeroCartao = it },
                label = { Text("Número do Cartão") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                leadingIcon = { Icon(Icons.Default.CreditCard, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = validade,
                onValueChange = { validade = it },
                label = { Text("Validade (MM/AA)") },
                placeholder = { Text("08/29") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = codigoCV,
                onValueChange = { codigoCV = it },
                label = { Text("Código de Verificação (CVV)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Ou escolha uma carteira digital:", fontSize = 18.sp, fontWeight = FontWeight.Medium)

            Button(
                onClick = { /* ação Google Wallet */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4)),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Google Wallet", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { /* ação Samsung Pay */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1428A0)),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Samsung Pay", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = { /* ação Mercado Pago */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF009EE3)),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Mercado Pago", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = gray),
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Voltar", color = Color.White)
            }
        }
    }
}


