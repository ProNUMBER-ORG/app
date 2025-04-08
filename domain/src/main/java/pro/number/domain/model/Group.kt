package pro.number.domain.model

data class Group(
    val id: Int,
    val name: String,
    val dateOfTheEvent: String,
    val itemsCount: Int,
    val tips: Byte,
    val waiter: String,
    val total: Int
)
