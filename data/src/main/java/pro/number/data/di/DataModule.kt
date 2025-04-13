package pro.number.data.di

import dagger.Module

@Module(includes = [DatabaseModule::class, RepositoryModule::class])
interface DataModule