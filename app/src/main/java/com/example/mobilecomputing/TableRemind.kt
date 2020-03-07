package com.example.mobilecomputing

import androidx.room.*

@Entity(tableName = "reminder")
data class Remind (
    @PrimaryKey(autoGenerate = true) var uid: Int?,
    @ColumnInfo(name = "time") var time: Long?,
    @ColumnInfo(name = "location") var location: String?,
    @ColumnInfo(name = "message") var message: String

)

@Dao
interface RemindDao {
    @Transaction
    @Insert
    fun insert(remind: Remind): Long

    @Query("SELECT * FROM reminder")
    fun getReminders(): List<Remind>

    @Query("DELETE FROM reminder WHERE uid = :id")
    fun delete(id: Int)
}