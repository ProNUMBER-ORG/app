package pro.number.domain.usecase

import pro.number.domain.repository.NetworkRepository
import javax.inject.Inject

class GetBillUseCase @Inject constructor(private val networkRepository: NetworkRepository) {
    suspend operator fun invoke(id: String) = networkRepository.getBill(id)
}