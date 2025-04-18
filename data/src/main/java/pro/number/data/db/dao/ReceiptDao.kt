package pro.number.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pro.number.data.db.entity.ReceiptItemEntity

@Dao
interface ReceiptDao {

    @Query("SELECT * FROM receipt_items WHERE id = :groupId")
    fun getReceiptsByGroupId(groupId: Long): Flow<List<ReceiptItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReceiptItem(receiptItem: ReceiptItemEntity)

    @Query("DELETE FROM receipt_items WHERE id = :receiptItemId")
    fun deleteReceiptItemById(receiptItemId: Int)

}