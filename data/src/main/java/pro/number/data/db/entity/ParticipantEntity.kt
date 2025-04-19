package pro.number.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import pro.number.domain.model.Participant

@Entity(
    tableName = "participants",
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
data class ParticipantEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "group_id")
    val groupId: Long,
    @ColumnInfo(name = "name")
    val name: String
) {
    fun toParticipant(): Participant = Participant(
        id = id,
        name = name
    )

    companion object {
        fun fromParticipant(participant: Participant, groupId: Long): ParticipantEntity =
            ParticipantEntity(
                id = participant.id,
                name = participant.name,
                groupId = groupId
            )
    }
}