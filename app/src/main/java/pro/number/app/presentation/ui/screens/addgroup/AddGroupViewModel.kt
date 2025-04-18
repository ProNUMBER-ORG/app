package pro.number.app.presentation.ui.screens.addgroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.number.domain.model.Participant
import javax.inject.Inject

class AddGroupViewModel @Inject constructor(): ViewModel() {

    private val _participants = MutableStateFlow<List<Participant>>(emptyList())
    val participants
        get() = _participants.asStateFlow()

    fun removeParticipantById(participantId: Int) {
        viewModelScope.launch {

        }
    }

}