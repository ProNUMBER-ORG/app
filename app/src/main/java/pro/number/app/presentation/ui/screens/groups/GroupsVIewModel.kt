package pro.number.app.presentation.ui.screens.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.number.domain.model.Group
import pro.number.domain.usecase.AddGroupUseCase
import pro.number.domain.usecase.GetGroupsUseCase
import javax.inject.Inject

class GroupsViewModel @Inject constructor(
    private val addGroupUseCase: AddGroupUseCase,
    private val getGroupsUseCase: GetGroupsUseCase
) : ViewModel() {

    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups
        get() = _groups.asStateFlow()

    fun addGroup(onSuccess: (Long) -> Unit) {
        viewModelScope.launch { onSuccess(addGroupUseCase(Group())) }
    }

    fun getGroups() {
        viewModelScope.launch {
            getGroupsUseCase().collect {
                _groups.value = it
            }
        }
    }

}