package juarez.roberto.mylibrary.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "users"
)
data class User(
    @PrimaryKey
    val id: Int,
    val name: String
)
