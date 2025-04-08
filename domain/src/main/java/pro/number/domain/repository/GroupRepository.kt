package pro.number.domain.repository

import pro.number.domain.model.Group

interface GroupRepository {

    suspend fun getGroups(): List<Group>

    suspend fun getGroupById(id: Int): Group

    suspend fun addGroup(group: Group)

    suspend fun deleteGroupById(id: Int)

}