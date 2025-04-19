package pro.number.domain.usecase

import pro.number.domain.model.Participant
import pro.number.domain.repository.GroupRepository
import javax.inject.Inject

class AddParticipantToGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {

    suspend operator fun invoke(participant: Participant, groupId: Long) {
        groupRepository.addParticipantInGroup(participant, groupId)
    }

}