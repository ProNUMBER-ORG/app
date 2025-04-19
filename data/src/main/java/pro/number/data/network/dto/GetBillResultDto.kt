package pro.number.data.network.dto

import pro.number.domain.model.Additional
import pro.number.domain.model.GetBillResult

data class GetBillResultDto(
    val id: String,
    val status: Int,
    val link: String,
    val additional: List<AdditionalDto>,
    val error: Any
) {
    fun toGetBillResult() = GetBillResult(
        id = id,
        status = status,
        link = link,
        additional = additional.map { Additional(it.name, it.cost) },
        error = error
    )
}

data class AdditionalDto(val name: String, val cost: Float)
data class ErrorDto(val reason: String)