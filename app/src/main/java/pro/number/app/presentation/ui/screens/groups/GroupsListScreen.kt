package pro.number.app.presentation.ui.screens.groups

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.number.app.presentation.ViewModelFactory
import pro.number.app.presentation.ui.components.GroupItem
import pro.number.app.presentation.ui.theme.AppTheme

@Composable
fun GroupsListScreen(
    viewModelFactory: ViewModelFactory
) {
    val viewModel = remember(Unit) {
        viewModelFactory.create(GroupsViewModel::class.java)
    }
    val groups = viewModel.groups.collectAsState()

    Scaffold(
        topBar = {
            Header(
                modifier = Modifier.padding(all = 20.dp)
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                contentPadding = PaddingValues(horizontal = 30.dp, vertical = 25.dp),
                shape = ShapeDefaults.Medium.copy(all = CornerSize(20.dp)),
                onClick = { TODO("Клик на кнопку добавления новой группы") }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
                Text(
                    text = "Добавить новую группу",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
            LazyColumn {
                items(groups.value, key = { it.id }) {
                    GroupItem(
                        groupName = it.name,
                        dateOfTheEvent = it.dateOfTheEvent,
                        itemsCount = it.itemsCount,
                        tips = it.tips,
                        waiter = it.waiter,
                        total = it.total
                    ) { TODO("Клик на кнопку детальной информации о группе") }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewGroupsListScreen() {
    AppTheme {
        GroupsListScreen(
            ViewModelFactory.createForPreview(GroupsViewModel())
        )
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Список групп",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun PreviewHeader() {
    AppTheme {
        Header()
    }
}