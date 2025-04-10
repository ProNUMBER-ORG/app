package pro.number.domain.usecase

import pro.number.domain.model.Group
import pro.number.domain.repository.GroupRepository
import javax.inject.Inject

class AddGroupUseCase @Inject constructor(private val groupRepository: GroupRepository) {
    suspend operator fun invoke(group: Group) {
        groupRepository.addGroup(group)
    }
}