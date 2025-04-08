package pro.number.app.presentation.ui.screens.groups

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import pro.number.domain.model.Group
import javax.inject.Inject

class GroupsViewModel @Inject constructor() : ViewModel() {

    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups
        get() = _groups.asStateFlow()

}