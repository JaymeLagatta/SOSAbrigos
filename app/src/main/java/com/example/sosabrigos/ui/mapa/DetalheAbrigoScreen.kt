package com.example.sosabrigos.ui.mapa

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sosabrigos.ui.home.gray


@Composable
fun DetalheAbrigoScreen(
    nomeAbrigo: String?,
    navController: NavController
) {
    val abrigo = when (nomeAbrigo) {
        "Abrigo Central" -> Abrigo("Abrigo Central", "Centro", 120, "Necessita alimentos", "(11) 91234-5678", 36, 0.0, 0.0, 100, 30, 100, 33)
        "Abrigo Esperança" -> Abrigo("Abrigo Esperança", "Bela Vista", 80, "Necessita cobertores", "(11) 92345-6789", 54, 0.0, 0.0, 72, 40, 50, 52)
        "Abrigo São José" -> Abrigo("Abrigo São José", "Vila Mariana", 45, "Necessita água", "(11) 93456-7890", 54, 0.0, 0.0, 42, 50, 80, 47)
        else -> null
    }

    abrigo?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Cabeçalho com nome do abrigo
            Text(
                text = it.nome,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Seção de informações básicas
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InfoItem(icon = Icons.Default.People, text = "Pessoas acolhidas: ${it.qtdPessoas}")
                    InfoItem(icon = Icons.Default.Bed, text = "Leitos: ${it.leitos} (${it.ocupados} ocupados)")
                    InfoItem(icon = Icons.Default.Phone, text = "Telefone: ${it.telefone}")
                    InfoItem(icon = Icons.Default.Place, text = "Localização: ${it.bairro}")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Seção de necessidades
            Text(
                text = "Necessidades Atuais",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Indicadores de necessidades
            NecessidadeIndicator("Alimentos", it.alimentos, Color(0xFF4CAF50))
            NecessidadeIndicator("Água", it.agua, Color(0xFF2196F3))
            NecessidadeIndicator("Cobertores", it.cobertores, Color(0xFFFF9800))

            Spacer(modifier = Modifier.weight(1f))

            @Composable
            fun ActionButton(
                text: String,
                color: Color,
                icon: ImageVector,
                onClick: () -> Unit
            ) {
                Button(
                    onClick = onClick,
                    colors = ButtonDefaults.buttonColors(containerColor = color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    Icon(imageVector = icon, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = MaterialTheme.typography.titleMedium.fontSize),
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(3.dp))

            // Botões na parte inferior
            Column(
                modifier = Modifier
                    .padding(16.dp).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                // Botão Ver no Mapa
                ActionButton(
                    text = "Ver no Mapa",
                    color = Color(0xFF2196F3),
                    icon = Icons.Default.Map,
                    onClick = {navController.navigate("mapa")
                    }
                )

                // Botão Pedir Doações
                ActionButton(
                    text = "Compartilhe nas Redes",
                    color = Color(0xFF4CAF50),
                    icon = Icons.Default.Send,
                    onClick = { /* Pedir doações */ }
                )

                // Botão Enviar Doações
                ActionButton(
                    text = "Enviar Doações",
                    color = Color(0xFFFF9800),
                    icon = Icons.Default.AddCircle,
                    onClick = {navController.navigate("ajuda")
                    }
                )
                // Botão Voluntário
                ActionButton(
                    text = "Seja um Voluntário!",
                    color = Color(0xFF8b0000),
                    icon = Icons.Default.People,
                    onClick = {navController.navigate("ajuda")}
                )
                // Botão Voltar
                ActionButton(
                    text = "Voltar",
                    color = gray,
                    icon = Icons.Default.ArrowBack,
                    onClick = { navController?.popBackStack() }
                )
            }
        }
    } ?: Text("Abrigo não encontrado.", style = MaterialTheme.typography.headlineMedium)
}

// Componente para itens de informação
@Composable
fun InfoItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}


// Componente para barras de necessidade
@Composable
fun NecessidadeIndicator(nome: String, porcentagem: Int, color: Color) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(text = nome, style = MaterialTheme.typography.bodyLarge)
        LinearProgressIndicator(
            progress = porcentagem / 100f,
            color = color,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text("$porcentagem%", style = MaterialTheme.typography.labelSmall)
    }
}

