package pro.number.domain.model

data class ReceiptItem(
    val id: Int? = null,
    val groupId: Long,
    val productName: String,
    val price: Float,
    val quantity: Int,
    val participants: List<ParticipantWithQuantity>
)
