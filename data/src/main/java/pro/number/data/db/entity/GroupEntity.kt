package pro.number.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pro.number.domain.model.Group

@Entity(tableName = "groups")
internal data class GroupEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "date_of_the_event")
    val dateOfTheEvent: String,
    @ColumnInfo(name = "items_count")
    val itemsCount: Int,
    @ColumnInfo(name = "tips")
    val tips: Byte,
    @ColumnInfo(name = "waiter")
    val waiter: String,
    @ColumnInfo(name = "total")
    val total: Int
) {

    fun toGroup(): Group {
        return Group(
            id = id,
            name = name,
            dateOfTheEvent = dateOfTheEvent,
            itemsCount = itemsCount,
            tips = tips,
            waiter = waiter,
            total = total
        )
    }

    companion object {
        fun fromGroup(group: Group) : GroupEntity {
            return GroupEntity(
                id = group.id,
                name = group.name,
                dateOfTheEvent = group.dateOfTheEvent,
                itemsCount = group.itemsCount,
                tips = group.tips,
                waiter = group.waiter,
                total = group.total
            )
        }
    }
}