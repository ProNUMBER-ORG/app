package pro.number.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import pro.number.data.db.dao.ParticipantDao
import pro.number.data.db.dao.ReceiptDao
import pro.number.data.db.dao.ReceiptParticipantDao
import pro.number.data.db.entity.ParticipantEntity
import pro.number.data.db.entity.ReceiptItemEntity
import pro.number.data.db.entity.ReceiptParticipantEntity
import pro.number.domain.model.Participant
import pro.number.domain.model.ParticipantWithQuantity
import pro.number.domain.model.ReceiptItem
import pro.number.domain.repository.ReceiptRepository
import javax.inject.Inject

class ReceiptRepositoryImpl @Inject constructor(
    private val receiptDao: ReceiptDao,
    private val participantDao: ParticipantDao,
    private val receiptParticipantDao: ReceiptParticipantDao
) : ReceiptRepository {
    override suspend fun getReceipts(groupId: Long): Flow<List<ReceiptItem>> =
        receiptDao.getReceiptsByGroupId(groupId).map { receiptEntities ->
            receiptEntities.map { receiptItem ->
                val participants = participantDao.getParticipantsByReceiptItemId(receiptItem.id)
                    .map { participantEntities ->
                        participantEntities.map { participantEntity ->
                            val quantity = receiptParticipantDao.getQuantity(
                                receiptItem.id,
                                participantEntity.id
                            )
                            ParticipantWithQuantity(participantEntity.toParticipant(), quantity)
                        }
                    }.first()
                receiptItem.toReceipt(participants)
            }
        }

    override suspend fun addReceipt(receipt: ReceiptItem) {
        receiptDao.addReceiptItem(ReceiptItemEntity.fromReceipt(receipt))
    }

    override suspend fun deleteReceiptById(id: Int) {
        receiptDao.deleteReceiptItemById(id)
    }

    override suspend fun addParticipantToReceipt(
        groupId: Long,
        receiptId: Int,
        participant: Participant,
        quantity: Int
    ) {
        participantDao.addParticipant(ParticipantEntity.fromParticipant(participant, groupId))
        receiptParticipantDao.addQuantity(
            ReceiptParticipantEntity(
                receiptId,
                participant.id,
                quantity
            )
        )

    }

    override suspend fun updateParticipantQuantity(
        participantId: Int,
        receiptId: Int,
        quantity: Int
    ) {
        receiptParticipantDao.addQuantity(
            ReceiptParticipantEntity(
                receiptId,
                participantId,
                quantity
            )
        )
    }

    override suspend fun deleteParticipantFromReceiptItem(
        participantId: Int,
        receiptId: Int
    ) {
        receiptParticipantDao.delete(receiptId = receiptId, participantId = participantId)
    }

    override suspend fun getParticipantsByGroupId(groupId: Long): Flow<List<Participant>> {
        return participantDao.getParticipantsByGroupId(groupId).map { entities ->
            entities.map { it.toParticipant() }
        }
    }
}