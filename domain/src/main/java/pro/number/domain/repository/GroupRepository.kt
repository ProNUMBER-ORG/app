package pro.number.domain.repository

import pro.number.domain.model.Group
import kotlinx.coroutines.flow.Flow

interface GroupRepository {

    fun getGroups(): Flow<List<Group>>

    fun getGroupById(id: Long): Flow<Group>

    suspend fun addGroup(group: Group): Long

    suspend fun deleteGroupById(id: Long)

}