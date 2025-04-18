package pro.number.domain.usecase

import pro.number.domain.model.Participant
import pro.number.domain.repository.ReceiptRepository
import javax.inject.Inject

class UpdateParticipantQuantityUseCase @Inject constructor(
    private val repository: ReceiptRepository
) {

    suspend operator fun invoke(participantId: Int, receiptId: Int, quantity: Int) {
        repository.updateParticipantQuantity(participantId, receiptId, quantity)
    }

}