package com.example.sosabrigos

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sosabrigos.ui.ajuda.AjudaScreen
import com.example.sosabrigos.ui.ajuda.DoarCartaoScreen
import com.example.sosabrigos.ui.ajuda.DoarPixScreen
import com.example.sosabrigos.ui.home.HomeScreen
import com.example.sosabrigos.ui.login.LoginScreen
import com.example.sosabrigos.ui.mapa.DetalheAbrigoScreen
import com.example.sosabrigos.ui.mapa.MapaAbrigosScreen
import com.example.sosabrigos.ui.mapa.MapaScreen
import com.example.sosabrigos.ui.perfil.CadastroUsuarioScreen
import com.example.sosabrigos.ui.perfil.PerfilScreen


@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(navController)
        }
        composable("perfil") {
            PerfilScreen(navController = navController)
        }
        composable("cadastro") {
            CadastroUsuarioScreen(navController) }

        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("mapa_abrigos") {
            MapaAbrigosScreen(navController = navController)
        }
        composable("ajuda") {
            AjudaScreen(navController = navController)
        }
        composable("doar_pix") {
            DoarPixScreen(navController)
        }
        composable("doar_cartao") {
            DoarCartaoScreen(navController)
        }
        composable("mapa") {
            MapaScreen(
                latitude = -23.5505,
                longitude = -46.6333,
                onVoltar = { navController.popBackStack() }
            )
        }


        composable(
            route = "detalhe_abrigo/{nomeAbrigo}",
            arguments = listOf(navArgument("nomeAbrigo") {
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry ->
            DetalheAbrigoScreen(
                nomeAbrigo = backStackEntry.arguments?.getString("nomeAbrigo"),
                navController = navController
            )
        }
    }
}
