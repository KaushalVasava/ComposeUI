package com.kaushalvasava.app.composeui.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kaushalvasava.app.composeui.screen.ProductDetailScreen
import com.kaushalvasava.app.composeui.screen.ProductsScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.PRODUCTS,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(400)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                tween(400)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(400)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(400)
            )
        }
    ) {
        composable(NavigationItem.PRODUCTS) {
            ProductsScreen(navController)
        }
        composable(NavigationItem.PRODUCT_DETAILS + "/{productId}", arguments = listOf(
            navArgument("productId") {
                type = NavType.StringType
            }
        )) {
            val productId = it.arguments?.getString("productId")
            if (productId != null)
                ProductDetailScreen(productId = productId, navController = navController)
        }
    }
}