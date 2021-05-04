package juarez.roberto.mylibrary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import juarez.roberto.mylibrary.models.User

@Database(entities = [User::class], version = 1)
abstract class MasterDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var instance: MasterDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MasterDatabase::class.java,
                "master.db"
            ).build()
    }
}