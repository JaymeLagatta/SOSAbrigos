package com.example.sosabrigos.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.VolunteerActivism
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sosabrigos.ui.components.CardItem
import com.example.sosabrigos.ui.components.ResourceTable
import kotlinx.coroutines.launch

// Definições de cores personalizadas
val green = Color(0xFF388E3C)
val orange = Color(0xFFFF9800)
val red = Color(0xFF8b0000)
val gray = Color(0xFF757575)
val grayescuro = Color(0xFF212121)
val brown = Color(0xFF64320b)
val blue = Color (0xFF2196F3)
val darkblue = Color (0xFF000080)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val menuItems = listOf(
        "Visão Geral", "Mapa de Abrigos", "Como Ajudar", "Perfil",
        "Alertas", "Sair"
    )
    var selectedItem by remember { mutableStateOf("Visão Geral") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.background(grayescuro)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    menuItems.forEach { item ->
                        Text(
                            text = item,
                            color = Color (0xFF212121),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                                .clickable {
                                    selectedItem = item
                                    coroutineScope.launch { drawerState.close() }

                                    when (item) {
                                        "Visão Geral" -> navController.navigate("home")
                                        "Mapa de Abrigos" -> navController.navigate("mapa_abrigos")
                                        "Como Ajudar" -> navController.navigate("ajuda")
                                        "Perfil" -> navController.navigate("perfil")
                                        "Sair" -> navController.navigate("login")
                                    }
                                }
                        )
                    }

                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("SOS Abrigos - Painel de Gestão", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF102E50))
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                // Grade de Cards
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    item {
                        CardItem(
                            title = "Abrigos Ativos",
                            value = "14",
                            backgroundColor = blue,
                            icon = Icons.Default.Home,
                            modifier = Modifier.clickable {
                                navController.navigate("mapa_abrigos")
                            }
                        )
                    }
                    item {
                        CardItem(
                            title = "Pessoas Acolhidas",
                            value = "842",
                            backgroundColor = orange,
                            icon = Icons.Default.Groups
                        )
                    }
                    item {
                        CardItem(
                            title = "Recursos Críticos",
                            value = "3",
                            backgroundColor = red,
                            icon = Icons.Default.Warning
                        )
                    }
                    item {
                        CardItem(
                            title = "Voluntários Hoje",
                            value = "56",
                            backgroundColor = gray,
                            icon = Icons.Default.VolunteerActivism
                        )
                    }
                }


                Spacer(modifier = Modifier.height(24.dp))

                // Título adicional
                Text(
                    "Recursos Disponíveis",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                // Tabela de recursos
                ResourceTable()

                Spacer(modifier = Modifier.weight(1f)) // Empurra o botão para o final da tela

                // Botão "QUERO AJUDAR"
                Button(
                    onClick = { navController.navigate("ajuda") },
                    colors = ButtonDefaults.buttonColors(containerColor = green),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(64.dp)
                ) {
                    Text(
                        "QUERO AJUDAR",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )

                }

            }
        }
    }
}

