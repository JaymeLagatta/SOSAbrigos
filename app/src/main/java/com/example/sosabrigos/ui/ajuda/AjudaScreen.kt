package com.example.sosabrigos.ui.ajuda

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sosabrigos.ui.home.gray
import com.example.sosabrigos.ui.home.red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjudaScreen(navController: NavController) {

    val opcoes = listOf(
        "PIX", "CARTÃO", "DOAR ALIMENTOS",
        "DOAR MATERIAIS", "DIVULGAR NAS REDES", "QUERO SER VOLUNTÁRIO",
        "DOAR AGASALHO", "PATROCINAR UM LEITO", "AJUDAR O BANHO MÓVEL",
        "HORTA COMUNITÁRIA"
    )

    val rotas = listOf( // rotas customizáveis
        "doar_pix", "doar_cartao", "doar_alimentos",
        "doar_materiais", "divulgar", "voluntario",
        "doar_agasalho", "patrocinar_leito", "banho_movel",
        "horta_comunitaria"
    )

    val cores = listOf( // cores customizáveis
        Color(0xFF309898), // acqua
        Color(0xFF388E3C), // verde
        Color(0xFFFF9800), // laranja
        Color(0xFF2196F3), // azul
        Color(0xFF6A1B9A), // roxo
        Color(0xFF00838F), // ciano escuro
        Color(0xFFff5f77), // rosa
        Color(0xFF98a355), // verde musgo
        Color(0xFF212121), // cinza escuro
        Color(0xFF104911)  // verde escuro
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "SOS Abrigos - Como Ajudar",
                        fontSize = 22.sp, // customizável
                        fontWeight = FontWeight.Normal
                    )
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
                .fillMaxSize()
        ) {
            Text(
                text = "Escolha uma forma de ajudar:",
                fontSize = 20.sp, // customizável
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                itemsIndexed(opcoes) { index, opcao ->
                    val cor = cores.getOrElse(index) { Color.Gray }
                    val rota = rotas.getOrElse(index) { "" }

                    Button(
                        onClick = {
                            if (rota.isNotBlank()) {
                                navController.navigate(rota)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = cor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        shape = MaterialTheme.shapes.medium // customizável
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = opcao,
                                color = Color.White,
                                fontSize = 18.sp, // customizável
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("perfil") },
                colors = ButtonDefaults.buttonColors(containerColor = red),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Meu Perfil",
                    color = Color.White,
                    fontSize = 16.sp, // customizável
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = gray),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(
                    text = "Voltar",
                    color = Color.White,
                    fontSize = 16.sp, // customizável
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
