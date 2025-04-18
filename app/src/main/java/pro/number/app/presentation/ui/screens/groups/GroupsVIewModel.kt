package pro.number.app.presentation.ui.screens.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.number.domain.model.Group
import javax.inject.Inject

class GroupsViewModel @Inject constructor(

) : ViewModel() {

    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups
        get() = _groups.asStateFlow()

    fun addGroup() {
        viewModelScope.launch {

        }
    }

}