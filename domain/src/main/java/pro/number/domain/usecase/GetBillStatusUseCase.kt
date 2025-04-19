package pro.number.domain.usecase

import pro.number.domain.repository.NetworkRepository
import javax.inject.Inject

class GetBillStatusUseCase @Inject constructor(private val networkRepository: NetworkRepository) {
    suspend operator fun invoke(id: String) = networkRepository.getBillStatus(id)
}