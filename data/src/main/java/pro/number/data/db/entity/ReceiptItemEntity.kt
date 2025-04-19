package pro.number.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import pro.number.domain.model.ParticipantWithQuantity
import pro.number.domain.model.ReceiptItem

@Entity(
    tableName = "receipt_items",
    foreignKeys = [
        ForeignKey(
            entity = GroupEntity::class,
            parentColumns = ["id"],
            childColumns = ["group_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("group_id")]
)
data class ReceiptItemEntity(
    @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "group_id")
    val groupId: Long,
    @ColumnInfo(name = "product_name")
    val productName: String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "quantity")
    val quantity: Int
) {

    fun toReceipt(participants: List<ParticipantWithQuantity>): ReceiptItem = ReceiptItem(
        id = id,
        groupId = groupId,
        productName = productName,
        price = price,
        quantity = quantity,
        participants = participants
    )

    companion object {
        fun fromReceipt(receiptItem: ReceiptItem): ReceiptItemEntity = ReceiptItemEntity(
            id = receiptItem.id,
            groupId = receiptItem.groupId,
            productName = receiptItem.productName,
            price = receiptItem.price,
            quantity = receiptItem.quantity,
        )

    }

}