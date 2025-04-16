package pro.number.domain.model

data class ReceiptItem(
    val id: Int,
    val productName: String,
    val price: Int,
    val quantity: Int
)
