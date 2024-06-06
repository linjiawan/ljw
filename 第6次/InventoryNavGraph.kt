package com.example.zxy.ui.navigation

import HomeDestination
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.zxy.ui.home.HomeScreen
import com.example.zxy.ui.item.ItemEditDestination
import com.example.zxy.ui.item.ItemEditScreen
import com.example.zxy.ui.item.ItemEntryDestination
import com.example.zxy.ui.item.ItemEntryScreen
import com.example.zxy.ui.item.ItemDetailsDestination
import com.example.zxy.ui.item.ItemDetailsScreen

@Composable
fun InventoryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController, startDestination = HomeDestination.route, modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToItemUpdate = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                })
        }
        composable(route = ItemEntryDestination.route) {
            ItemEntryScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemDetailsScreen(
                navigateToEditItem =
                {
                    navController.navigate("${ItemEditDestination.route}/$it")
                },
                navigateBack = { navController.navigateUp() })
        }
        composable(
            route = ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}