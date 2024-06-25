
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ex.messreview.Screens.AuthScreen
import com.ex.messreview.Screens.HomeScreen
import com.ex.messreview.Screens.ProfileScreen
import com.ex.messreview.Screens.RatingScreen
import com.ex.messreview_admin.Screens.CatererScreen
import com.ex.messreview_admin.Screens.MessTypeScreen
import com.ex.messreview_admin.Screens.itemEditScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auth_screen") {
        composable("auth_screen") {
            AuthScreen(
                onLoginClicked = { username, password ->
                    // Handle login logic here, then navigate to caterer_screen
                    navController.navigate("caterer_screen")
                }
            )
        }
        composable("caterer_screen") {
            CatererScreen(
                navController = navController,
                onCatererSelected = { catererName ->
                    // Navigate to mess_type_screen and pass selected caterer name
                    navController.navigate("mess_type_screen/$catererName")
                }
            )
        }
        composable("profile_screen") {
            ProfileScreen()
        }
        composable(
            "mess_type_screen/{catererName}",
            arguments = listOf(navArgument("catererName") { type = NavType.StringType })
        ) { backStackEntry ->
            MessTypeScreen(
                navController = navController,
                catererName = backStackEntry.arguments?.getString("catererName") ?: ""
            )
        }
        composable(
            "home_screen/{catererName}/{messType}",
            arguments = listOf(
                navArgument("catererName") { type = NavType.StringType },
                navArgument("messType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            HomeScreen(
                navController = navController,
                catererName = backStackEntry.arguments?.getString("catererName") ?: "",
                messType = backStackEntry.arguments?.getString("messType") ?: ""
            )

        }
        composable(
            route = "rating_screen/{itemName}/{imageResId}",
            arguments = listOf(
                navArgument("itemName") { type = NavType.StringType },
                navArgument("imageResId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val itemName = backStackEntry.arguments?.getString("itemName")
            val imageResId = backStackEntry.arguments?.getInt("imageResId")
            if (itemName != null && imageResId != null) {
                RatingScreen(itemName = itemName, imageResId = imageResId)
            }
        }
        composable(
            route = "item_edit_screen/{dayOfWeek}/{mealTime}/{itemName}",
            arguments = listOf(
                navArgument("dayOfWeek") { type = NavType.StringType },
                navArgument("mealTime") { type = NavType.StringType },
                navArgument("itemName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val dayOfWeek = backStackEntry.arguments?.getString("dayOfWeek") ?: ""
            val mealTime = backStackEntry.arguments?.getString("mealTime") ?: ""
            val itemName = backStackEntry.arguments?.getString("itemName") ?: ""
            itemEditScreen(dayOfWeek = dayOfWeek, mealTime = mealTime, itemName = itemName)
        }


    }
}
