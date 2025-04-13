package pro.number.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import pro.number.data.db.AppDatabase
import pro.number.data.db.dao.GroupDao
import javax.inject.Singleton

@Module
internal class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    fun provideGroupDao(appDatabase: AppDatabase): GroupDao {
        return appDatabase.groupDao()
    }

}