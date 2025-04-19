package pro.number.domain.usecase

import pro.number.domain.repository.GroupRepository
import javax.inject.Inject

class DeleteGroupUseCase @Inject constructor(private val groupRepository: GroupRepository) {
    suspend operator fun invoke(id: Long) {
        groupRepository.deleteGroupById(id)
    }
}