package com.example.sosabrigos.ui.perfil

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// --- API Correios simples (ViaCEP) ---
interface CepApi {
    @GET("{cep}/json/")
    suspend fun buscarEndereco(@Path("cep") cep: String): CepResponse
}

data class CepResponse(
    val cep: String?,
    val logradouro: String?,
    val complemento: String?,
    val bairro: String?,
    val localidade: String?,  // cidade
    val uf: String?           // estado
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroUsuarioScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    // Degradê azul marinho
    val darkBlue = Color(0xFF102E50)
    val blue = Color(0xFF1E88E5)

    // Estados dos campos e foco para animação das bordas
    var nome by remember { mutableStateOf("") }
    var nomeFocus by remember { mutableStateOf(false) }

    var telefone by remember { mutableStateOf("") }
    var telefoneFocus by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var emailFocus by remember { mutableStateOf(false) }

    var cep by remember { mutableStateOf("") }
    var cepFocus by remember { mutableStateOf(false) }

    var logradouro by remember { mutableStateOf("") }
    var logradouroFocus by remember { mutableStateOf(false) }

    var complemento by remember { mutableStateOf("") }
    var complementoFocus by remember { mutableStateOf(false) }

    var bairro by remember { mutableStateOf("") }
    var bairroFocus by remember { mutableStateOf(false) }

    var cidade by remember { mutableStateOf("") }
    var cidadeFocus by remember { mutableStateOf(false) }

    var estado by remember { mutableStateOf("") }
    var estadoFocus by remember { mutableStateOf(false) }

    // Retrofit ViaCEP
    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val cepApi = retrofit.create(CepApi::class.java)

    suspend fun buscarCep(cep: String) {
        try {
            val resposta = cepApi.buscarEndereco(cep)
            logradouro = resposta.logradouro ?: ""
            complemento = resposta.complemento ?: ""
            bairro = resposta.bairro ?: ""
            cidade = resposta.localidade ?: ""
            estado = resposta.uf ?: ""
        } catch (e: Exception) {
            // Erro simples: não popula campos
        }
    }

    // Controle da animação da tela (fadeIn + slide)
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    // Animação do botão Salvar
    var buttonClicked by remember { mutableStateOf(false) }
    val buttonColor by animateColorAsState(
        targetValue = if (buttonClicked) Color(0xFF1B5E20) else Color(0xFF388E3C),
        animationSpec = tween(300)
    )
    val buttonScale by animateFloatAsState(
        targetValue = if (buttonClicked) 1.05f else 1f,
        animationSpec = tween(300)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(darkBlue, blue.copy(alpha = 0.8f)),
                    startY = 0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
            .padding(32.dp)
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(800)) + slideInVertically(initialOffsetY = { it / 2 })
        ) {
            Surface(
                color = Color.White,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        "Cadastro",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkBlue,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Função para criar cada campo com animação de borda
                    @Composable
                    fun AnimatedOutlinedTextField(
                        value: String,
                        onValueChange: (String) -> Unit,
                        label: String,
                        keyboardType: KeyboardType = KeyboardType.Text,
                        isFocused: Boolean,
                        onFocusChange: (Boolean) -> Unit
                    ) {
                        val borderColor by animateColorAsState(
                            targetValue = if (isFocused) blue else Color.LightGray
                        )
                        OutlinedTextField(
                            value = value,
                            onValueChange = onValueChange,
                            label = { Text(label) },
                            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged { onFocusChange(it.isFocused) },
                            colors = outlinedTextFieldColors(
                                focusedBorderColor = borderColor,
                                unfocusedBorderColor = borderColor,
                                cursorColor = blue
                            )
                        )
                    }

                    AnimatedOutlinedTextField(
                        value = nome,
                        onValueChange = { nome = it },
                        label = "Nome",
                        isFocused = nomeFocus,
                        onFocusChange = { nomeFocus = it }
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedOutlinedTextField(
                        value = telefone,
                        onValueChange = { telefone = it },
                        label = "Telefone",
                        keyboardType = KeyboardType.Phone,
                        isFocused = telefoneFocus,
                        onFocusChange = { telefoneFocus = it }
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedOutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        keyboardType = KeyboardType.Email,
                        isFocused = emailFocus,
                        onFocusChange = { emailFocus = it }
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedOutlinedTextField(
                        value = cep,
                        onValueChange = {
                            val filtered = it.filter { c -> c.isDigit() }.take(8)
                            cep = filtered
                            if (filtered.length == 8) {
                                coroutineScope.launch { buscarCep(filtered) }
                            }
                        },
                        label = "CEP",
                        keyboardType = KeyboardType.Number,
                        isFocused = cepFocus,
                        onFocusChange = { cepFocus = it }
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedOutlinedTextField(
                        value = logradouro,
                        onValueChange = { logradouro = it },
                        label = "Logradouro",
                        isFocused = logradouroFocus,
                        onFocusChange = { logradouroFocus = it }
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedOutlinedTextField(
                        value = complemento,
                        onValueChange = { complemento = it },
                        label = "Complemento",
                        isFocused = complementoFocus,
                        onFocusChange = { complementoFocus = it }
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedOutlinedTextField(
                        value = bairro,
                        onValueChange = { bairro = it },
                        label = "Bairro",
                        isFocused = bairroFocus,
                        onFocusChange = { bairroFocus = it }
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedOutlinedTextField(
                        value = cidade,
                        onValueChange = { cidade = it },
                        label = "Cidade",
                        isFocused = cidadeFocus,
                        onFocusChange = { cidadeFocus = it }
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedOutlinedTextField(
                        value = estado,
                        onValueChange = { estado = it },
                        label = "Estado",
                        isFocused = estadoFocus,
                        onFocusChange = { estadoFocus = it }
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            buttonClicked = true
                            Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()
                            navController.navigate("home")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                scaleX = buttonScale
                                scaleY = buttonScale
                            },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                    ) {
                        Text(
                            "Salvar",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}
