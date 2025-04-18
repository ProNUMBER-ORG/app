package pro.number.domain.usecase

import kotlinx.coroutines.flow.Flow
import pro.number.domain.model.Group
import pro.number.domain.repository.GroupRepository
import javax.inject.Inject

class GetGroupByIdUseCase @Inject constructor(private val groupRepository: GroupRepository) {
    operator fun invoke(id: Long): Flow<Group> {
        return groupRepository.getGroupById(id)
    }
}