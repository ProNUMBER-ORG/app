package pro.number.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pro.number.data.db.entity.GroupEntity

@Dao
internal interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGroup(groupEntity: GroupEntity): Long

    @Query("SELECT * FROM `groups`")
    fun getGroups() : Flow<List<GroupEntity>>

    @Query("SELECT * FROM `groups` WHERE id = :id LIMIT 1")
    fun getGroup(id: Long) : Flow<GroupEntity>

    @Query("DELETE FROM `groups` WHERE id = :id")
    suspend fun deleteGroup(id: Long)

}