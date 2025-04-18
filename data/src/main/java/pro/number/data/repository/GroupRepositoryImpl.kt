package pro.number.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pro.number.data.db.dao.GroupDao
import pro.number.data.db.entity.GroupEntity
import pro.number.domain.model.Group
import pro.number.domain.repository.GroupRepository
import javax.inject.Inject

internal class GroupRepositoryImpl @Inject constructor(private val groupsDao: GroupDao) :
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
}