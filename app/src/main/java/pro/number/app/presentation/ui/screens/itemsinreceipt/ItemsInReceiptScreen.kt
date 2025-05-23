package pro.number.app.presentation.ui.screens.itemsinreceipt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pro.number.app.presentation.ViewModelFactory
import pro.number.app.presentation.ui.components.Header
import pro.number.app.presentation.ui.components.ReceiptItem

@Composable
fun ItemsInReceiptScreen(
    viewModelFactory: ViewModelFactory,
    groupId: Long,
    onBackClickListener: () -> Unit,
    onToMainMenuClick: () -> Unit
) {

    val viewModel = remember(Unit) {
        viewModelFactory.create(ItemsInReceiptViewModel::class.java)
    }

    val receiptItems = viewModel.receiptItems.collectAsState()

    val availableParticipants = viewModel.availableParticipants.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchData(groupId)
        viewModel.loadParticipants(groupId)
    }

    Scaffold(
        topBar = {
            Header(
                title = "Позиция в чеке", onBackClickListener = onBackClickListener
            )
        }
    ) {
        Box(contentAlignment = Alignment.BottomCenter) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = 25.dp)
            ) {
                items(receiptItems.value) { receipt ->
                    ReceiptItem(
                        modifier = Modifier.padding(vertical = 5.dp),
                        productName = receipt.productName,
                        totalPrice = receipt.price,
                        quantity = receipt.quantity,
                        participants = receipt.participants,
                        availableParticipants = availableParticipants.value,
                        onAddParticipantClick = {
                            viewModel.addParticipantToReceipt(it, receipt.id ?: 0)
                        },
                        onQuantityChange = { participant, quantity ->
                            viewModel.updateQuantity(
                                participant.id, receipt.id ?: 0, quantity
                            )
                        },
                        onDeleteClick = {
                            viewModel.deleteParticipant(it.id, receipt.id ?: 0)
                        })
                }
            }
            Button(
                modifier = Modifier
                    .padding(25.dp),
                onClick = onToMainMenuClick
            ) {
                Text(
                    text = "Готово",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

    }
}
