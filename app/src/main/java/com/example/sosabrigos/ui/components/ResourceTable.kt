package com.example.sosabrigos.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit

@Composable
fun ResourceTable() {
    val resources = listOf(
        listOf("Água (litros)", "500", "1000"),
        listOf("Alimento (kg)", "250", "500"),
        listOf("Cobertores", "120", "30"),
        listOf("Kits Médicos", "80", "15"),
        listOf("Máscaras", "350", "200")
    )

    Column {
        // Cabeçalho
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("Recurso", "Estoque", "Necessidade").forEach {
                Text(
                    text = it,
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            }
        }

        Divider()

        // Conteúdo da tabela
        resources.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                row.forEachIndexed { index, item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 6.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 16.sp
                    )
                }
                //Icon(
                //    Icons.Default.Edit,
                //    contentDescription = "Editar",
                //    modifier = Modifier.weight(0.5f)
                //)
            }
            Divider()
        }
    }
}
