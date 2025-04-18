package pro.number.domain.model

data class ReceiptItem(
    val id: Int,
    val groupId: Long,
    val productName: String,
    val price: Int,
    val quantity: Int,
    val participants: List<ParticipantWithQuantity>
)
