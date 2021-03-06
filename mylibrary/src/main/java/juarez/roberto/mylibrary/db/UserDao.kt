package juarez.roberto.mylibrary.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import juarez.roberto.mylibrary.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: List<User>)
}