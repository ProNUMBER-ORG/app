package pro.number.app.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pro.number.app.presentation.ui.screens.addgroup.AddGroupViewModel
import pro.number.app.presentation.ui.screens.groups.GroupsViewModel
import pro.number.app.presentation.ui.screens.itemsinreceipt.ItemsInReceiptViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GroupsViewModel::class)
    fun bindGroupsViewModel(viewModel: GroupsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddGroupViewModel::class)
    fun bindAddGroupViewModel(viewModel: AddGroupViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ItemsInReceiptViewModel::class)
    fun bindItemsInReceiptViewModel(viewModel: ItemsInReceiptViewModel): ViewModel

}