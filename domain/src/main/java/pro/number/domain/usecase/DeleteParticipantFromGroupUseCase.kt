package pro.number.domain.usecase

import pro.number.domain.repository.GroupRepository
import javax.inject.Inject

class DeleteParticipantFromGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {

    suspend operator fun invoke(participantId: Int) {
        groupRepository.deleteParticipantFromGroup(participantId)
    }

}