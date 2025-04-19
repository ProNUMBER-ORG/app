package pro.number.domain.usecase

import pro.number.domain.repository.NetworkRepository
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(private val networkRepository: NetworkRepository) {
    suspend operator fun invoke(imageName: String) = networkRepository.uploadImage(imageName)
}