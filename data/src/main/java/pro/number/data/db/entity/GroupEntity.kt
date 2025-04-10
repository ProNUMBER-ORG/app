package pro.number.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import pro.number.domain.model.Group

@Entity(tableName = "groups")
internal data class GroupEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dateOfTheEvent: String,
    val itemsCount: Int,
    val tips: Byte,
    val waiter: String,
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