package pro.number.domain.model

data class Participant(
    val id: Int = UNDEFINED_ID,
    val name: String
) {
    companion object {
        private const val UNDEFINED_ID = 0
    }
}

