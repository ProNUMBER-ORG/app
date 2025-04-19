package pro.number.data.network

import okhttp3.MultipartBody
import pro.number.data.network.dto.GetBillResultDto
import pro.number.data.network.dto.GetBillStatusResultDto
import pro.number.data.network.dto.UploadImageResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface Api {

    @Multipart
    @POST("bill/upload")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
    ): Response<UploadImageResultDto>

    @GET("bill/{id}")
    suspend fun getBill(@Path("id") id: String): Response<GetBillResultDto>

    @GET("bill/status/{id}")
    suspend fun getBillStatus(@Path("id") id: String): Response<GetBillStatusResultDto>

}