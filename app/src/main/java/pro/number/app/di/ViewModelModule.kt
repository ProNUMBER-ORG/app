package pro.number.app.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pro.number.app.presentation.ui.screens.groups.GroupsViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GroupsViewModel::class)
    fun bindGroupsViewModel(viewModel: GroupsViewModel): ViewModel

}