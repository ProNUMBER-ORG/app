package pro.number.app.presentation.ui.navigation

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import pro.number.app.presentation.ViewModelFactory
import pro.number.app.presentation.ui.screens.addgroup.AddGroupScreen
import pro.number.app.presentation.ui.screens.groups.GroupsListScreen
import pro.number.app.presentation.ui.screens.itemsinreceipt.ItemsInReceiptScreen
import pro.number.app.presentation.ui.screens.makeCheckPhoto.MakeCheckPhotoScreen
import pro.number.app.presentation.ui.screens.scancheck.ScanCheckScreen


@Serializable
object GroupsList

@Serializable
data class ItemsInReceiptList(val groupId: Long)

@Serializable
data class AddGroup(val groupId: Long)

@Serializable
data class ScanCheck(val groupId: Long, val imageName: String, val needRotate: Boolean)

@Serializable
data class MakeCheckPhoto(val imageName: String?)

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
            val backStackEntry = remember(it) {
                navHostController.getBackStackEntry(AddGroup(it.toRoute<AddGroup>().groupId))
            }
            val scope = rememberCoroutineScope()
            val context = LocalContext.current

            var needRotate by remember { mutableStateOf(false) }

            val pickerImagePath = MutableStateFlow<String?>(null)
            val photoImagePath = backStackEntry.savedStateHandle.getStateFlow<String?>("imagePath", null)

            val imagePath = merge(
                pickerImagePath.onEach {
                    needRotate = false
                },
                photoImagePath.onEach {
                    needRotate = true
                }
            ).collectAsState(initial = null)

            val galleryLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                getRealPathFromMediaUri(context, uri)?.let {
                    scope.launch {
                        pickerImagePath.emit(it)
                    }
                }
            }

            AddGroupScreen(
                groupId = groupId,
                imagePath = imagePath,
                viewModelFactory = viewModelFactory,
                onBackClickListener = { navHostController.popBackStack() },
                onTakePhotoClickListener = {
                    navHostController.navigate(route = MakeCheckPhoto(null))
                },
                onPickFromGalleryClickListener = {
                    galleryLauncher.launch("image/*")
                },
                onContinueClickListener = { groupId, imageName ->
                    navHostController.navigate(
                        route = ScanCheck(
                            groupId = groupId,
                            imageName = imageName,
                            needRotate = needRotate
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
                onToMainMenuClick = {
                    navHostController.navigate(route = GroupsList) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                },
                onBackClickListener = { navHostController.popBackStack() }
            )
        }
        composable<ScanCheck> {
            val groupId = it.toRoute<ScanCheck>().groupId
            val imageName = it.toRoute<ScanCheck>().imageName
            val needRotate = it.toRoute<ScanCheck>().needRotate
            ScanCheckScreen(
                viewModelFactory = viewModelFactory,
                needRotate = needRotate,
                groupId = groupId,
                imageName = imageName,
                onContinueClick = {
                    navHostController.navigate(route = ItemsInReceiptList(groupId = it))
                },
                addNewCheck = {
                    navHostController.navigate(
                        route = MakeCheckPhoto(it)
                    )
                },
                onBackClick = { navHostController.popBackStack() }
            )
        }
        composable<MakeCheckPhoto> {
            val imageName = it.toRoute<MakeCheckPhoto>().imageName
            MakeCheckPhotoScreen(
                imageName = imageName,
                popBack = { navHostController.popBackStack() },
                onImageCaptured = {
                    navHostController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("imagePath", it)
                    navHostController.popBackStack()
                }
            )
        }
    }
}

fun getRealPathFromMediaUri(context: Context, uri: Uri?): String? {
    if(uri == null) return null
    val projection = arrayOf(MediaStore.MediaColumns.DATA)
    val cursor = context.contentResolver.query(uri, projection, null, null, null)
    val columnIndex = cursor?.getColumnIndex(MediaStore.MediaColumns.DATA)

    return try {
        if (cursor != null) {
            cursor.moveToFirst()
            val filePath = columnIndex?.let { cursor.getString(it) }
            cursor.close()
            filePath
        } else null
    } catch (exception: Exception) {
        null
    }
}