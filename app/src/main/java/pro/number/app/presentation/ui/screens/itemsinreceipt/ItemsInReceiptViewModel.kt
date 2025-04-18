package pro.number.app.presentation.ui.screens.itemsinreceipt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.number.domain.model.Participant
import pro.number.domain.model.ReceiptItem
import pro.number.domain.usecase.AddParticipantToReceiptUseCase
import pro.number.domain.usecase.DeleteParticipantFromReceiptItemUseCase
import pro.number.domain.usecase.GetParticipantsByGroupIdUseCase
import pro.number.domain.usecase.GetReceiptsByGroupIdUseCase
import pro.number.domain.usecase.UpdateParticipantQuantityUseCase
import javax.inject.Inject

class ItemsInReceiptViewModel @Inject constructor(
    private val getReceiptsByGroupIdUseCase: GetReceiptsByGroupIdUseCase,
    private val getParticipantsByGroupIdUseCase: GetParticipantsByGroupIdUseCase,
    private val addParticipantToReceiptUseCase: AddParticipantToReceiptUseCase,
    private val updateParticipantQuantityUseCase: UpdateParticipantQuantityUseCase,
    private val deleteParticipantFromReceiptItemUseCase: DeleteParticipantFromReceiptItemUseCase
) : ViewModel() {

    private var currentGroupId: Long? = null

    private val _receiptItems =
        MutableStateFlow<List<ReceiptItem>>(emptyList())

    val receiptItems
        get() = _receiptItems.asStateFlow()

    private val _availableParticipants = MutableStateFlow<List<Participant>>(emptyList())
    val availableParticipants = _availableParticipants.asStateFlow()

    fun loadParticipants(groupId: Long) {
        viewModelScope.launch {
            getParticipantsByGroupIdUseCase(groupId).collect {
                _availableParticipants.value = it
            }
        }
    }

    fun fetchData(groupId: Long) {
        currentGroupId = groupId
        viewModelScope.launch {
            getReceiptsByGroupIdUseCase(groupId).collect {
                _receiptItems.value = it
            }
        }
    }

    fun addParticipantToReceipt(
        participant: Participant,
        receiptId: Int,
        quantity: Int = MIN_QUANTITY
    ) {
        viewModelScope.launch {
            currentGroupId?.let {
                addParticipantToReceiptUseCase(
                    participant = participant,
                    groupId = it,
                    receiptId = receiptId,
                    quantity = quantity
                )
                fetchData(it)
            }


        }
    }

    fun updateQuantity(
        participantId: Int,
        receiptId: Int,
        quantity: Int
    ) {
        viewModelScope.launch {
            updateParticipantQuantityUseCase(participantId, receiptId, quantity)
            currentGroupId?.let { fetchData(it) }
        }
    }

    fun deleteParticipant(participantId: Int, receiptId: Int) {
        viewModelScope.launch {
            deleteParticipantFromReceiptItemUseCase(participantId, receiptId)
            currentGroupId?.let { fetchData(it) }
        }
    }

    companion object {
        private const val MIN_QUANTITY = 1
    }

}