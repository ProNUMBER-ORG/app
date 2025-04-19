package pro.number.domain.repository

import pro.number.domain.model.GetBillResult
import pro.number.domain.model.GetBillStatusResult
import pro.number.domain.model.UploadImageResult

interface NetworkRepository {
    suspend fun uploadImage(imageFilePath: String): Result<UploadImageResult>
    suspend fun getBill(id: String): Result<GetBillResult>
    suspend fun getBillStatus(id: String): Result<GetBillStatusResult>
}