package pro.number.app.presentation.ui.screens.addgroup

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.number.app.R
import pro.number.app.presentation.ViewModelFactory
import pro.number.app.presentation.ui.components.Header
import pro.number.app.presentation.ui.navigation.MakeCheckPhoto
import pro.number.app.presentation.ui.theme.AppTheme
import pro.number.domain.model.Participant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGroupScreen(
    groupId: Long,
    imagePath: State<String?>,
    viewModelFactory: ViewModelFactory,
    onTakePhotoClickListener: () -> Unit,
    onPickFromGalleryClickListener: () -> Unit,
    onContinueClickListener: (groupId: Long, imageName: String) -> Unit,
    onBackClickListener: () -> Unit
) {
    val viewModel = remember(Unit) {
        viewModelFactory.create(AddGroupViewModel::class.java)
    }
    var isBottomSheetIsVisible by remember { mutableStateOf(false) }

    val participants = viewModel.participants.collectAsState()

    if (isBottomSheetIsVisible) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetIsVisible = false }
        ) {
            val textFieldState = rememberTextFieldState()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GroupSearchBar(
                    textFieldState = textFieldState,
                    onSearch = {

                    }
                )
            }
        }
    }

    Scaffold(
        topBar = {
            Header(
                title = "Новая группа #$groupId",
                onBackClickListener = onBackClickListener
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 25.dp)
        ) {
            TextButton(
                onClick = { isBottomSheetIsVisible = !isBottomSheetIsVisible }
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Добавить участника",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                ParticipantChipsGrid(
                    modifier = Modifier.fillMaxSize(),
                    participants = participants.value,
                    onLongClick = {
                        viewModel.removeParticipantById(it.id)
                    }
                )
            }

            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = ShapeDefaults.Medium.copy(all = CornerSize(14.dp)),
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            modifier = Modifier.heightIn(max = 100.dp),
                            painter = painterResource(id = R.drawable.receipt),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Добавить чек",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        HorizontalDivider(modifier = Modifier.padding(top = 16.dp))
                        TextButton(onClick = {
                            onTakePhotoClickListener()
                        }) {
                            Text(
                                text = "Сделать фото",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        HorizontalDivider()
                        TextButton(onClick = onPickFromGalleryClickListener) {
                            Text(
                                text = "Выбрать из галереи",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }
            }

            Button(
                onClick = { imagePath.value?.let { onContinueClickListener(groupId, it) } },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 62.dp),
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = MaterialTheme.colorScheme.tertiary),
                enabled = imagePath.value != null
            ) {
                Text(text = "Далее", fontSize = 16.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupSearchBar(
    modifier: Modifier = Modifier,
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit
) {
    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
        inputField = {
            SearchBarDefaults.InputField(
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                },
                query = textFieldState.text.toString(),
                onQueryChange = {
                    textFieldState.edit { replace(0, length, it) }
                },
                onSearch = { onSearch(textFieldState.text.toString()) },
                expanded = false,
                onExpandedChange = { false },
                placeholder = {
                    Text(text = "Имя")
                }
            )
        },
        expanded = false,
        onExpandedChange = { },
    ) {
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun AddGroupScreenPreview() {
    AppTheme {
        AddGroupScreen(
            groupId = 1L,
            imagePath = mutableStateOf<String?>(null),
            ViewModelFactory.createForPreview(AddGroupViewModel()),
            onTakePhotoClickListener = { },
            onPickFromGalleryClickListener = { },
            onContinueClickListener = { _, _ -> },
            onBackClickListener = { }
        )
    }
}

@Composable
fun ParticipantChip(
    modifier: Modifier = Modifier,
    name: String,
    onLongClickListener: () -> Unit
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { onLongClickListener }
                )
            },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color.Gray),
        color = Color.Transparent
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = name,
            fontSize = 15.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun ParticipantChipsGridPreview() {
    AppTheme {
        val mockParticipants = List(10) {
            Participant(it, name = "Петров. В")
        }
        ParticipantChipsGrid(
            participants = mockParticipants,
            onLongClick = { }
        )
    }
}

@Composable
fun ParticipantChipsGrid(
    modifier: Modifier = Modifier,
    participants: List<Participant>,
    onLongClick: (Participant) -> Unit
) {
    LazyVerticalGrid(modifier = modifier, columns = GridCells.Fixed(3)) {
        items(participants, key = { it.id }) {
            ParticipantChip(
                name = it.name,
                onLongClickListener = { onLongClick(it) }
            )
        }
    }
}