package pro.number.data.di

import dagger.Binds
import dagger.Module
import pro.number.data.repository.GroupRepositoryImpl
import pro.number.data.repository.ReceiptRepositoryImpl
import pro.number.domain.repository.GroupRepository
import pro.number.domain.repository.ReceiptRepository

@Module
internal interface RepositoryModule {

    @Binds
    fun bindGroupRepository(groupRepository: GroupRepositoryImpl) : GroupRepository

    fun bindReceiptRepository(repository: ReceiptRepositoryImpl): ReceiptRepository

}