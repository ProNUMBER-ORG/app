package pro.number.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "receipt_participant",
    primaryKeys = ["receipt_item_id", "participant_id"],
    foreignKeys = [
        ForeignKey(
            entity = ReceiptItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["receipt_item_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ParticipantEntity::class,
            parentColumns = ["id"],
            childColumns = ["participant_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("receipt_item_id"), Index("participant_id")]
)
data class ReceiptParticipantEntity(
    @ColumnInfo(name = "receipt_item_id")
    val receiptItemId: Int,
    @ColumnInfo(name = "participant_id")
    val participantId: Int,
    @ColumnInfo(name = "quantity")
    val quantity: Int
)
