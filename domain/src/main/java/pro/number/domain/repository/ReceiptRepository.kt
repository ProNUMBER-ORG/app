package pro.number.domain.repository

import pro.number.domain.model.ReceiptItem

interface ReceiptRepository {

    suspend fun getReceipts(): List<ReceiptItem>

    suspend fun getReceiptById(id: Int): ReceiptItem

    suspend fun addReceipt(receipt: ReceiptItem)

    suspend fun deleteReceiptById(id: Int)

}