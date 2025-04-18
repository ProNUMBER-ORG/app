package pro.number.app.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import pro.number.app.presentation.ViewModelFactory
import pro.number.app.presentation.ui.screens.addgroup.AddGroupScreen
import pro.number.app.presentation.ui.screens.groups.GroupsListScreen
import pro.number.app.presentation.ui.screens.itemsinreceipt.ItemsInReceiptScreen


@Serializable
object GroupsList

@Serializable
data class ItemsInReceiptList(val groupId: Int)

@Serializable
object AddGroup

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory
) {
    NavHost(
        navHostController,
        startDestination = GroupsList,
        modifier = modifier
    ) {
        composable<GroupsList> {
            GroupsListScreen(
                viewModelFactory = viewModelFactory,
                onAddGroupClick = {
                    navHostController.navigate(route = AddGroup)
                },
                onGroupClick = {
                    navHostController.navigate(route = ItemsInReceiptList(groupId = it))
                })
        }
        composable<AddGroup> {
            AddGroupScreen(
                viewModelFactory = viewModelFactory,
                onBackClickListener = { navHostController.popBackStack() },
                onTakePhotoClickListener = {

                },
                onPickFromGalleryClickListener = {

                },
                onContinueClickListener = {
                    navHostController.navigate(route = ItemsInReceiptList(it))
                }
            )
        }
        composable<ItemsInReceiptList> {
            val groupId = it.toRoute<Int>()
            ItemsInReceiptScreen(
                viewModelFactory = viewModelFactory,
                groupId = groupId,
                onBackClickListener = { navHostController.popBackStack() }
            )
        }
    }
}