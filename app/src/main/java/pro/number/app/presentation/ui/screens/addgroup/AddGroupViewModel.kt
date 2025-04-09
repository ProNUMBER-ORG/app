package pro.number.app.presentation.ui.screens.addgroup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pro.number.domain.model.Participant

class AddGroupViewModel : ViewModel() {

    private val _participants = MutableStateFlow<List<Participant>>(emptyList())
    val participants
        get() = _participants.asStateFlow()


}