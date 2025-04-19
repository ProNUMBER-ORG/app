package pro.number.app.presentation.ui.screens.addgroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.number.domain.model.Participant
import pro.number.domain.usecase.AddParticipantToGroupUseCase
import pro.number.domain.usecase.DeleteParticipantFromGroupUseCase
import pro.number.domain.usecase.DeleteParticipantFromReceiptItemUseCase
import pro.number.domain.usecase.GetParticipantsByGroupIdUseCase
import javax.inject.Inject

class AddGroupViewModel @Inject constructor(
    private val addParticipantToGroupUseCase: AddParticipantToGroupUseCase,
    private val getParticipantsByGroupIdUseCase: GetParticipantsByGroupIdUseCase,
    private val deleteParticipantFromGroupUseCase: DeleteParticipantFromGroupUseCase
): ViewModel() {

    private val _participants = MutableStateFlow<List<Participant>>(emptyList())
    val participants
        get() = _participants.asStateFlow()

    fun removeParticipantById(participantId: Int) {
        viewModelScope.launch {
            deleteParticipantFromGroupUseCase(participantId)
        }
    }

    fun addParticipant(name: String, groupId: Long) {
        viewModelScope.launch {
            addParticipantToGroupUseCase(Participant(name = name), groupId)
        }
    }

    fun getParticipantsByGroupId(groupId: Long) {
        viewModelScope.launch {
            getParticipantsByGroupIdUseCase(groupId).collect {
                _participants.value = it
            }
        }
    }

}