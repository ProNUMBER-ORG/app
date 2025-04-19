package pro.number.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pro.number.data.db.dao.GroupDao
import pro.number.data.db.dao.ParticipantDao
import pro.number.data.db.dao.ReceiptDao
import pro.number.data.db.dao.ReceiptParticipantDao
import pro.number.data.db.entity.GroupEntity
import pro.number.data.db.entity.ParticipantEntity
import pro.number.data.db.entity.ReceiptItemEntity
import pro.number.data.db.entity.ReceiptParticipantEntity

@Database(
    entities = [
        GroupEntity::class, ParticipantEntity::class,
        ReceiptItemEntity::class, ReceiptParticipantEntity::class
    ],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun groupDao(): GroupDao

    abstract fun receiptDao(): ReceiptDao

    abstract fun participantDao(): ParticipantDao

    abstract fun receiptParticipantDao(): ReceiptParticipantDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

private const val DATABASE_NAME = "app_database"