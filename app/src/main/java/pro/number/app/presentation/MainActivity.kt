package pro.number.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import pro.number.app.App
import pro.number.app.presentation.ui.navigation.AppNavHost
import pro.number.app.presentation.ui.theme.AppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Surface {
                    val navController = rememberNavController()
                    AppNavHost(
                        navHostController = navController,
                        viewModelFactory = viewModelFactory,
                    )
                }
            }
        }
    }
}