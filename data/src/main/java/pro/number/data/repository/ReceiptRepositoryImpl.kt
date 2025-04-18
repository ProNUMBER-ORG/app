package pro.number.data.repository

import kotlinx.coroutines.flow.Flow
import pro.number.domain.model.Participant
import pro.number.domain.model.ReceiptItem
import pro.number.domain.repository.ReceiptRepository
import javax.inject.Inject

class ReceiptRepositoryImpl @Inject constructor() : ReceiptRepository{
    override suspend fun getReceipts(groupId: Int): Flow<List<ReceiptItem>> {
        TODO()
    }

    override suspend fun getReceiptById(id: Int): Flow<ReceiptItem> {
        TODO("Not yet implemented")
    }

    override suspend fun addReceipt(
        groupId: Int,
        receipt: ReceiptItem
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteReceiptById(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun addParticipantToReceipt(
        receiptId: Int,
        participant: Participant,
        quantity: Int
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun updateParticipantQuantity(
        participantId: Int,
        receiptId: Int,
        quantity: Int
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteParticipantFromReceiptItem(
        participantId: Int,
        receiptId: Int
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getParticipantsByGroupId(groupId: Int): Flow<List<Participant>> {
        TODO("Not yet implemented")
    }
}