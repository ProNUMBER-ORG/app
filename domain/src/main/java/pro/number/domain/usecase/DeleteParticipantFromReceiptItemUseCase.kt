package pro.number.domain.usecase

import pro.number.domain.repository.ReceiptRepository
import javax.inject.Inject

class DeleteParticipantFromReceiptItemUseCase @Inject constructor(
    private val repository: ReceiptRepository
) {

    suspend operator fun invoke(participantId: Int, receiptId: Int) {
        repository.deleteParticipantFromReceiptItem(participantId, receiptId)
    }

}