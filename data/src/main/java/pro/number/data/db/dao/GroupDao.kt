package pro.number.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pro.number.data.db.entity.GroupEntity
import pro.number.data.db.model.GroupWithDetails

@Dao
internal interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGroup(groupEntity: GroupEntity): Long


    @Query("""
        SELECT g.*,
                (SELECT COUNT(*) FROM receipt_items r WHERE r.group_id = g.id) as items_count,
                (SELECT SUM(r.price * r.quantity) FROM receipt_items r WHERE r.group_id = g.id) AS total
        FROM `groups` g
    """)
    fun getGroups(): Flow<List<GroupWithDetails>>

    @Query("""
        SELECT 
            g.*,
            (SELECT COUNT(*) FROM receipt_items r WHERE r.group_id = g.id) as items_count,
            (SELECT SUM(r.price * r.quantity) FROM receipt_items r WHERE r.group_id = g.id) AS total
        FROM `groups` g
        WHERE g.id = :id
        LIMIT 1
    """)
    fun getGroup(id: Long): Flow<GroupWithDetails>

    @Query("DELETE FROM `groups` WHERE id = :id")
    suspend fun deleteGroup(id: Long)

}