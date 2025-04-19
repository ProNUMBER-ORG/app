package pro.number.data.repository

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import pro.number.data.network.Api
import pro.number.domain.model.GetBillResult
import pro.number.domain.model.GetBillStatusResult
import pro.number.domain.model.UploadImageResult
import pro.number.domain.repository.NetworkRepository
import java.io.File
import javax.inject.Inject

internal class NetworkRepositoryImpl @Inject constructor(private val api: Api): NetworkRepository {
    override suspend fun uploadImage(imageFilePath: String): Result<UploadImageResult> = try {
        val file = File(imageFilePath)
        val image = MultipartBody.Part.createFormData("image", file.name, file.asRequestBody("image/*".toMediaTypeOrNull()))
        val response = api.uploadImage(image)
        val result = response.body()?.let { UploadImageResult(it.id, it.url) }
        if(response.isSuccessful && result != null) {
            Result.success(result)
        } else {
            Result.failure(Exception("Network Error"))
        }
    } catch (ex: Exception) {
        Result.failure(ex)
    }

    override suspend fun getBill(id: String): Result<GetBillResult> = try {
        val response = api.getBill(id)
        val result = response.body()?.toGetBillResult()
        if (response.isSuccessful && result != null) {
            Result.success(result)
        } else {
            Result.failure(Exception("Network Error"))
        }
    } catch (ex: Exception) {
        Result.failure(ex)
    }

    override suspend fun getBillStatus(id: String): Result<GetBillStatusResult> = try {
        val response = api.getBillStatus(id)
        val result = response.body()?.let { GetBillStatusResult(it.status) }
        if (response.isSuccessful && result != null) {
            Result.success(result)
        } else {
            Result.failure(Exception("Network Error"))
        }
    } catch (ex: Exception) {
        Result.failure(ex)
    }
}