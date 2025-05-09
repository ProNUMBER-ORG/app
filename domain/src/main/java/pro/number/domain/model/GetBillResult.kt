package pro.number.domain.model

data class GetBillResult(
    val id: String,
    val status: Int,
    val link: String,
    val additional: List<Additional>,
    val error: Any
)

data class Additional(val item: String, val cost: Float)
data class Error(val reason: String)