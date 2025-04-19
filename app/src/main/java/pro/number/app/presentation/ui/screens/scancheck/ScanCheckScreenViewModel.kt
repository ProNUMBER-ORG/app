package pro.number.app.presentation.ui.screens.scancheck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.number.domain.model.Additional
import pro.number.domain.model.ReceiptItem
import pro.number.domain.repository.ReceiptRepository
import pro.number.domain.usecase.GetBillUseCase
import pro.number.domain.usecase.UploadImageUseCase
import javax.inject.Inject

class ScanCheckScreenViewModel @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val getBillUseCase: GetBillUseCase,
    private val receiptRepository: ReceiptRepository
) : ViewModel() {

    private val _statusFlow = MutableStateFlow<Status?>(null)
    val statusFlow = _statusFlow.asStateFlow()

    fun uploadImage(imageName: String, groupId: Long) {
        viewModelScope.launch {
            _statusFlow.emit(Status.InProgress)
            val result = uploadImageUseCase.invoke(imageName)
            try {
                val id = result.getOrThrow().id
                while (true) {
                    val getBillResult = getBillUseCase.invoke(id).getOrThrow()
                    if (getBillResult.status == 3) {
                        throw Exception()
                    } else if (getBillResult.status == 4) {
                        for (additional in getBillResult.additional) {
                            receiptRepository.addReceipt(
                                ReceiptItem(
                                    groupId = groupId,
                                    productName = additional.item,
                                    price = additional.cost,
                                    quantity = 1,
                                    participants = emptyList()
                                )
                            )
                        }
                        _statusFlow.emit(Status.Success(getBillResult.additional))
                        break
                    }
                    delay(1000)
                }
            } catch (ex: Exception) {
                _statusFlow.emit(Status.Error)
            }
        }
    }
}

sealed class Status {
    data object InProgress: Status()
    data object Error: Status()
    data class Success(
        val additional: List<Additional>
    ): Status()
}