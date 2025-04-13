package pro.number.data.di

import dagger.Binds
import dagger.Module
import pro.number.data.repository.GroupRepositoryImpl
import pro.number.domain.repository.GroupRepository

@Module
internal interface RepositoryModule {

    @Binds
    fun bindGroupRepository(groupRepository: GroupRepositoryImpl) : GroupRepository

}