package pro.number.domain.usecase

import pro.number.domain.repository.ReceiptRepository
import javax.inject.Inject

class GetParticipantsByGroupIdUseCase @Inject constructor(
    private val repository: ReceiptRepository
) {

    suspend operator fun invoke(groupId: Int) = repository.getParticipantsByGroupId(groupId)

}