package com.example.sosabrigos.ui.mapa

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MapaAbrigosScreen(navController: NavController) {
    val abrigos = listOf(
        Abrigo("Abrigo Central", "Centro", 120, "Necessita alimentos", "(11) 91234-5678", 36, 0.0, 0.0, 100, 30, 100, 33),
        Abrigo("Abrigo Esperança", "Bela Vista", 80, "Necessita cobertores", "(11) 92345-6789", 48, 0.0, 0.0, 72, 40, 50, 48),
        Abrigo("Abrigo São José", "Vila Mariana", 45, "Necessita leitos", "(11) 93456-7890", 54, 0.0, 0.0, 42, 50, 80, 47),
        Abrigo("Abrigo Tamojunto", "Grajaú", 30, "Necessita materiais", "(11) 93456-7890", 33, 0.0, 0.0, 42, 50, 80, 31),
        Abrigo("Abrigo Amparo", "Campo Limpo", 20, "Necessita alimentos", "(11) 93456-7890", 32, 0.0, 0.0, 42, 50, 80, 32),
        Abrigo("Abrigo Cerumano", "Perdizes", 50, "Necessita água", "(11) 93456-7890", 54, 0.0, 0.0, 42, 50, 80, 47),
        Abrigo("Abrigo Filho de Deus", "Jardim Ângela", 60, "Necessita água", "(11) 93456-7890", 54, 0.0, 0.0, 42, 50, 80, 47),
        Abrigo("Abrigo Oxossi", "Guaianases", 33, "Necessita cobertores", "(11) 93456-7890", 36, 0.0, 0.0, 42, 50, 80, 30),
        Abrigo("Abrigo Ganesha", "São Mateus", 41, "Necessita cobertores", "(11) 93456-7890", 40, 0.0, 0.0, 42, 50, 80, 38),
        Abrigo("Abrigo Maoxi", "São Mateus", 12, "Necessita alimentos", "(11) 93456-7890", 15, 0.0, 0.0, 42, 50, 80, 15),
        Abrigo("Abrigo Paulistano", "Vila Mariana", 18, "Necessita materiais", "(11) 93456-7890", 20, 0.0, 0.0, 42, 50, 80, 20),
        Abrigo("Abrigo Frei Luiz", "Centro", 9, "Necessita leitos", "(11) 93456-7890", 9, 0.0, 0.0, 42, 50, 80, 9),
        Abrigo("Abrigo Tupã", "Campo Limpo", 6, "Necessita leitos", "(11) 93456-7890", 12, 0.0, 0.0, 42, 50, 80, 12),
        Abrigo("Abrigo Chico Xá", "Guainases", 38, "Necessita alimentos", "(11) 93456-7890", 38, 0.0, 0.0, 42, 50, 80, 35)
    )

    val totalAbrigos = abrigos.size
    val totalLeitos = abrigos.sumOf { it.leitos }
    val totalOcupados = abrigos.sumOf { it.ocupados }
    val taxaOcupacao = if (totalLeitos > 0) (totalOcupados * 100) / totalLeitos else 0
    val totalVoluntarios = 56 // valor fictício

    var searchText by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Campo de busca
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Buscar abrigo...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Três boxes lado a lado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            InfoBox(
                title = "Abrigos",
                value = "$totalAbrigos",
                backgroundColor = Color(0xFF2196F3),
                modifier = Modifier.weight(1f)
            )
            InfoBox(
                title = "Ocupação",
                value = "$taxaOcupacao%",
                backgroundColor = Color(0xFFFF9800),
                modifier = Modifier.weight(1f)
            )
            InfoBox(
                title = "Voluntários",
                value = "$totalVoluntarios",
                backgroundColor = Color(0xFF757575),
                modifier = Modifier.weight(1f)
            )
        }




        Spacer(modifier = Modifier.height(16.dp))

        // Lista de abrigos
        Box(modifier = Modifier.weight(1f)) {
            LazyColumn {
                items(abrigos.filter { it.nome.contains(searchText, ignoreCase = true) }) { abrigo ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                navController.navigate("detalhe_abrigo/${abrigo.nome}")
                            },
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(abrigo.nome, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text("Bairro: ${abrigo.bairro}")
                            Text("Pessoas acolhidas: ${abrigo.qtdPessoas}")
                            Text("Leitos: ${abrigo.leitos}")
                            Text("Necessidade: ${abrigo.necessidade}")
                            Text("Telefone: ${abrigo.telefone}")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Rodapé com botões
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActionButton(
                text = "Cadastre seu abrigo",
                color = Color(0xFF2196F3),
                icon = Icons.Default.AddCircle,
                onClick = { /* TODO: Navegar para cadastro */ }
            )
            ActionButton(
                text = "Seja um voluntário",
                color = Color(0xFF8b0000),
                icon = Icons.Default.People,
                onClick = { /* TODO: Ação voluntário */ }
            )
            ActionButton(
                text = "Voltar",
                color = Color.Gray,
                icon = Icons.Default.ArrowBack,
                onClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun InfoBox(
    title: String,
    value: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(backgroundColor, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}




@Composable
fun ActionButton(
    text: String,
    color: Color,
    //fontSize: Int = 17,
    icon: ImageVector,
    onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Icon(icon, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color.White)
    }
}

data class Abrigo(
    val nome: String,
    val bairro: String,
    val qtdPessoas: Int,
    val necessidade: String,
    val telefone: String,
    val leitos: Int,
    val latitude: Double,
    val longitude: Double,
    val alimentos: Int,
    val cobertores: Int,
    val agua: Int,
    val ocupados: Int
)
