package pro.number.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pro.number.data.db.dao.GroupDao
import pro.number.data.db.entity.GroupEntity

@Database(entities = [GroupEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun groupDao() : GroupDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

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