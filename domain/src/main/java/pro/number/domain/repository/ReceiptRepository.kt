package pro.number.domain.repository

import kotlinx.coroutines.flow.Flow
import pro.number.domain.model.Participant
import pro.number.domain.model.ReceiptItem

interface ReceiptRepository {

    suspend fun getReceipts(groupId: Long): Flow<List<ReceiptItem>>

    suspend fun addReceipt(receipt: ReceiptItem)

    suspend fun deleteReceiptById(id: Int)

    suspend fun addParticipantToReceipt(groupId: Long, receiptId: Int, participant: Participant, quantity: Int)

    suspend fun updateParticipantQuantity(participantId: Int, receiptId: Int, quantity: Int)

    suspend fun deleteParticipantFromReceiptItem(participantId: Int, receiptId: Int)

    suspend fun getParticipantsByGroupId(groupId: Long): Flow<List<Participant>>

}