package pro.number.app.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.number.app.presentation.ui.theme.AppTheme
import pro.number.domain.model.Participant
import pro.number.domain.model.ParticipantWithQuantity

@Composable
fun ReceiptItem(
    modifier: Modifier = Modifier,
    productName: String,
    totalPrice: Int,
    quantity: Int,
    participants: List<ParticipantWithQuantity>,
    availableParticipants: List<Participant>,
    onAddParticipantClick: (Participant) -> Unit,
    onQuantityChange: (participant: Participant, quantity: Int) -> Unit,
    onDeleteClick: (Participant) -> Unit
) {
    var expanded by remember { mutableStateOf(true) }
    var isDialogVisible by remember { mutableStateOf(false) }

    val remainingCount = quantity - participants.sumOf { it.quantity }
    val alreadySelectedIds = participants.map { it.participant.id }
    val selectableParticipants = availableParticipants.filterNot { it.id in alreadySelectedIds }

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row {
                        Text(
                            text = "$productName,",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "$quantity шт.",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Text(
                        text = "+ разделить позицию с участником",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "$totalPrice руб.",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded)
                            Icons.Filled.KeyboardArrowUp
                        else
                            Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }

            AnimatedVisibility(expanded) {
                Column {
                    HorizontalDivider()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AddParticipantButton(
                            onAddParticipantClick = {
                                isDialogVisible = true
                            },
                            enabled = remainingCount > 0
                        )
                        Text(
                            text = "Осталось $remainingCount шт.",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    LazyColumn {
                        items(participants, key = { it.participant.id }) {
                            ParticipantItem(
                                participant = it.participant,
                                quantity = it.quantity.toString(),
                                remainingCount = remainingCount,
                                onQuantityChanged = { newQuantity ->
                                    onQuantityChange(it.participant, newQuantity)
                                },
                                onDeleteClick = { onDeleteClick(it.participant) }
                            )
                        }
                    }
                }
            }
        }
    }

    if (isDialogVisible) {
        ParticipantPickerDialog(
            availableParticipants = selectableParticipants,
            onParticipantSelected = {
                onAddParticipantClick(it)
                isDialogVisible = false
            },
            onDismiss = { isDialogVisible = false }
        )
    }
}

@Composable
private fun AddParticipantButton(
    modifier: Modifier = Modifier,
    onAddParticipantClick: () -> Unit,
    enabled: Boolean
) {
    TextButton(
        modifier = modifier,
        onClick = onAddParticipantClick,
        contentPadding = PaddingValues(0.dp),
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = null,
            tint = if (enabled)
                MaterialTheme.colorScheme.onSurface
            else
                MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Добавить участника",
            color = if (enabled)
                MaterialTheme.colorScheme.onSurface
            else
                MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun ParticipantItem(
    modifier: Modifier = Modifier,
    participant: Participant,
    quantity: String,
    remainingCount: Int,
    onQuantityChanged: (Int) -> Unit,
    onDeleteClick: () -> Unit
) {

    var localQuantity by remember { mutableStateOf(quantity) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = participant.name
            )
            OutlinedTextField(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 8.dp),
                value = localQuantity,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1,
                onValueChange = {
                    val enteredQuantity = it.toIntOrNull() ?: return@OutlinedTextField
                    if (enteredQuantity in 1..remainingCount) {
                        localQuantity = it
                        onQuantityChanged(enteredQuantity)
                    }
                }
            )
            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun ParticipantPickerDialog(
    availableParticipants: List<Participant>,
    onParticipantSelected: (Participant) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = {
            Text(text = "Выберите участника")
        },
        text = {
            Column {
                availableParticipants.forEach { participant ->
                    Text(
                        text = participant.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { onParticipantSelected(participant) }
                    )
                }
            }
        }
    )
}


@Preview
@Composable
private fun ParticipantItemPreview() {
    AppTheme {
        ParticipantItem(
            participant = Participant(id = 1, name = "Алексей Иванов"),
            onQuantityChanged = { },
            quantity = "0",
            remainingCount = 10,
            onDeleteClick = {}
        )
    }
}

@Preview
@Composable
private fun PreviewReceiptItem() {
    AppTheme {
        ReceiptItem(
            productName = "Салат ‘Каприз’",
            quantity = 10,
            totalPrice = 1500,
            participants = listOf(
                ParticipantWithQuantity(Participant(1, "Иван Морозов"), 4),
                ParticipantWithQuantity(Participant(2, "Вася Никитин"), 5)
            ),
            onAddParticipantClick = {},
            onQuantityChange = { _, _ -> },
            onDeleteClick = {},
            availableParticipants = listOf(Participant(3, "Никита Большой"))
        )
    }
}