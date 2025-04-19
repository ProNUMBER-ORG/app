package pro.number.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pro.number.data.db.entity.ReceiptParticipantEntity

@Dao
interface ReceiptParticipantDao {

    @Query("SELECT quantity FROM receipt_participant WHERE receipt_item_id = :receiptId AND participant_id = :participantId")
    suspend fun getQuantity(receiptId: Int, participantId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuantity(receiptParticipantEntity: ReceiptParticipantEntity)

    @Query("DELETE FROM receipt_participant WHERE receipt_item_id = :receiptId AND participant_id = :participantId")
    suspend fun delete(receiptId: Int, participantId: Int)

}