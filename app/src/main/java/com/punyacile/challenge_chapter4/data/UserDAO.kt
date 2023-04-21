package com.punyacile.challenge_chapter4.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {


    @Query("SELECT * FROM table_user WHERE email = :email AND password = :password")
    fun verifyUser(email : String, password : String): LiveData<User>

    @Insert
    fun insertUser(user: User)
}