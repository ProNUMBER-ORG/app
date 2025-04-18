package pro.number.domain.usecase

import kotlinx.coroutines.flow.Flow
import pro.number.domain.model.ReceiptItem
import pro.number.domain.repository.ReceiptRepository
import javax.inject.Inject

class GetReceiptsUseCase @Inject constructor(
    private val repository: ReceiptRepository
) {

    suspend operator fun invoke(groupId: Int): Flow<List<ReceiptItem>> =
        repository.getReceipts(groupId)

}