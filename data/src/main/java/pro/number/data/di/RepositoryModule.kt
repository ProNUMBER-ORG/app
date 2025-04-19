package pro.number.data.di

import dagger.Binds
import dagger.Module
import pro.number.data.repository.GroupRepositoryImpl
import pro.number.data.repository.NetworkRepositoryImpl
import pro.number.data.repository.ReceiptRepositoryImpl
import pro.number.domain.repository.GroupRepository
import pro.number.domain.repository.NetworkRepository
import pro.number.domain.repository.ReceiptRepository

@Module
internal interface RepositoryModule {

    @Binds
    fun bindGroupRepository(groupRepository: GroupRepositoryImpl) : GroupRepository

    @Binds
    fun bindReceiptRepository(repository: ReceiptRepositoryImpl): ReceiptRepository

    @Binds
    fun bindNetworkRepository(repository: NetworkRepositoryImpl): NetworkRepository

}