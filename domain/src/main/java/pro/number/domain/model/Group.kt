package pro.number.domain.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Group(
    val id: Long = UNDEFINED_ID,
    val name: String = BASE_NAME,
    val dateOfTheEvent: String = SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(Date()),
    val itemsCount: Int = DEFAULT_ITEM_COUNT,
    val tips: Byte = DEFAULT_TIPS,
    val waiter: String = DEFAULT_WAITER,
    val total: Int = DEFAULT_TOTAL
) {
    companion object {
        private const val UNDEFINED_ID = 0L
        private const val BASE_NAME = "Новая группа"
        private const val DEFAULT_ITEM_COUNT = 0
        private const val DEFAULT_TIPS = 0.toByte()
        private const val DEFAULT_TOTAL = 0
        private const val DATE_PATTERN = "yyyy-MM-dd"
        private const val DEFAULT_WAITER = ""
    }
}
