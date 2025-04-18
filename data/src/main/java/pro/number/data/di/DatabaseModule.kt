package pro.number.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import pro.number.data.db.AppDatabase
import pro.number.data.db.dao.GroupDao
import pro.number.data.db.dao.ParticipantDao
import pro.number.data.db.dao.ReceiptDao
import pro.number.data.db.dao.ReceiptParticipantDao
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

    @Provides
    fun provideReceiptItemDao(appDatabase: AppDatabase): ReceiptDao {
        return appDatabase.receiptDao()
    }

    @Provides
    fun provideParticipantDao(appDatabase: AppDatabase): ParticipantDao {
        return appDatabase.participantDao()
    }

    @Provides
    fun provideReceiptParticipantDao(appDatabase: AppDatabase): ReceiptParticipantDao {
        return appDatabase.receiptParticipantDao()
    }

}