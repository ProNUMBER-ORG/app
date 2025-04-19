package pro.number.domain.usecase

import pro.number.domain.model.Participant
import pro.number.domain.repository.ReceiptRepository
import javax.inject.Inject

class AddParticipantToReceiptUseCase @Inject constructor(
    private val repository: ReceiptRepository
) {

    suspend operator fun invoke(participant: Participant, receiptId: Int, quantity: Int) {
        repository.addParticipantToReceipt(
            receiptId = receiptId,
            participant = participant,
            quantity = quantity
        )
    }

}