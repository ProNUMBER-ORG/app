package pro.number.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import pro.number.data.db.entity.GroupEntity
import pro.number.domain.model.Group

data class GroupWithDetails(
    @Embedded val group: GroupEntity,
    @ColumnInfo(name = "items_count")
    val itemsCount: Int?,
    @ColumnInfo(name = "total")
    val total: Float?
) {
    fun toGroup(): Group = group.toGroup(itemsCount ?: 0, total ?: 0f)
}
