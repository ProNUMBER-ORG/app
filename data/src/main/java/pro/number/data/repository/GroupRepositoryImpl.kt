package pro.number.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pro.number.data.db.dao.GroupDao
import pro.number.data.db.dao.ParticipantDao
import pro.number.data.db.entity.GroupEntity
import pro.number.data.db.entity.ParticipantEntity
import pro.number.domain.model.Group
import pro.number.domain.model.Participant
import pro.number.domain.repository.GroupRepository
import javax.inject.Inject

internal class GroupRepositoryImpl @Inject constructor(
    private val groupsDao: GroupDao,
    private val participantDao: ParticipantDao
) :
    GroupRepository {
    override fun getGroups(): Flow<List<Group>> {
        return groupsDao.getGroups().map {
            it.map { entity -> entity.toGroup() }
        }
    }

    override fun getGroupById(id: Long): Flow<Group> {
        return groupsDao.getGroup(id).map {
            it.toGroup()
        }
    }

    override suspend fun addGroup(group: Group): Long =
        groupsDao.addGroup(GroupEntity.fromGroup(group))


    override suspend fun deleteGroupById(id: Long) {
        groupsDao.deleteGroup(id)
    }

    override suspend fun addParticipantInGroup(participant: Participant, groupId: Long) {
        participantDao.addParticipantInGroup(
            ParticipantEntity(
                participant.id,
                groupId,
                participant.name
            )
        )
    }

    override suspend fun deleteParticipantFromGroup(
        participantId: Int,
    ) {
        participantDao.deleteParticipantById(participantId)
    }


}