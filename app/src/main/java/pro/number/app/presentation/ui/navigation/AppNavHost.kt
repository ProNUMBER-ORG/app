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
import pro.number.app.presentation.ui.screens.scancheck.ScanCheckScreen


@Serializable
object GroupsList

@Serializable
data class ItemsInReceiptList(val groupId: Long)

@Serializable
data class AddGroup(val groupId: Long)

@Serializable
data class ScanCheck(val groupId: Long, val imageName: String)

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
                    navHostController.navigate(route = AddGroup(it))
                },
                onGroupClick = {
                    navHostController.navigate(route = ItemsInReceiptList(groupId = it))
                }
            )
        }
        composable<AddGroup> {
            val groupId = it.toRoute<AddGroup>().groupId
            AddGroupScreen(
                groupId = groupId,
                viewModelFactory = viewModelFactory,
                onBackClickListener = { navHostController.popBackStack() },
                onTakePhotoClickListener = {

                },
                onPickFromGalleryClickListener = {

                },
                onContinueClickListener = { groupId, imageName ->
                    navHostController.navigate(
                        route = ScanCheck(
                            groupId = groupId,
                            imageName = imageName
                        )
                    )
                }
            )
        }
        composable<ItemsInReceiptList> {
            val groupId = it.toRoute<ItemsInReceiptList>().groupId
            ItemsInReceiptScreen(
                viewModelFactory = viewModelFactory,
                groupId = groupId,
                onBackClickListener = { navHostController.popBackStack() }
            )
        }
        composable<ScanCheck> {
            val groupId = it.toRoute<ScanCheck>().groupId
            val imageName = it.toRoute<ScanCheck>().imageName
            ScanCheckScreen(
                viewModelFactory = viewModelFactory,
                groupId = groupId,
                imageName = imageName,
                onContinueClick = {
                    navHostController.navigate(route = ItemsInReceiptList(groupId = it))
                },
                onBackClick = { navHostController.popBackStack() }
            )
        }
    }
}