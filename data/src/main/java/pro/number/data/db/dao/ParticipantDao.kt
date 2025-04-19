package pro.number.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pro.number.data.db.entity.ParticipantEntity

@Dao
interface ParticipantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addParticipantInGroup(participantEntity: ParticipantEntity)

    @Query("DELETE FROM participants WHERE id = :participantId")
    suspend fun deleteParticipantById(participantId: Int)

    @Query("SELECT * FROM participants WHERE group_id = :groupId")
    fun getParticipantsByGroupId(groupId: Long): Flow<List<ParticipantEntity>>

    @Query("""
        SELECT p.* FROM participants p
        INNER JOIN receipt_participant rp ON p.id = rp.participant_id
        WHERE rp.receipt_item_id = :receiptItemId
    """)
    fun getParticipantsByReceiptItemId(receiptItemId: Int): Flow<List<ParticipantEntity>>

}