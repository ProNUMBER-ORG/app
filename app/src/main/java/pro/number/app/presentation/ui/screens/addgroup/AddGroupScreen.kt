package pro.number.app.presentation.ui.screens.addgroup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
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
import pro.number.app.presentation.ui.theme.AppTheme

@Composable
fun AddGroupScreen(
    viewModelFactory: ViewModelFactory
) {

    val viewModel = remember(Unit) {
        viewModelFactory.create(AddGroupViewModel::class.java)
    }

    val participants = viewModel.participants.collectAsState()

    Scaffold(
        topBar = {
            Header(
                title = "Новая группа",
                onBackClickListener = {
                    TODO("Действие при нажатии на кнопку возврата")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it)
                .padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            TextButton(
                onClick = { TODO("Клик на кнопку для добавления участников") }) {
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
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(participants.value, key = { it.id }) {
                    ParticipantChip(name = it.name) { TODO("Долгий клик на участника группы") }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp),
                color = MaterialTheme.colorScheme.surfaceContainerHighest,
                shape = ShapeDefaults.Medium.copy(all = CornerSize(14.dp))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                    TextButton(onClick = { TODO("Клик на кнопку сделать фото") }) {
                        Text(
                            text = "Сделать фото", fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                    HorizontalDivider()
                    TextButton(onClick = { TODO("Клик на кнопку выбрать из галереи") }) {
                        Text(
                            text = "Выбрать из галереи", fontSize = 17.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
            Button(
                onClick = { TODO("Кнопка далее") },
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 62.dp),
                colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = MaterialTheme.colorScheme.tertiary)
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

@Preview
@Composable
private fun AddGroupScreenPreview() {
    AppTheme {
        AddGroupScreen(ViewModelFactory.createForPreview(AddGroupViewModel()))
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
private fun ParticipantChipPreview() {
    AppTheme {
        LazyVerticalGrid(GridCells.Fixed(3)) {
            items(10) {
                ParticipantChip(name = "Петров В.") { }
            }
        }
    }
}