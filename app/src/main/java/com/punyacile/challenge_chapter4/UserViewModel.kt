package com.punyacile.challenge_chapter4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.punyacile.challenge_chapter4.data.NoteDatabase
import com.punyacile.challenge_chapter4.data.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class UserViewModel(app : Application) : AndroidViewModel(app) {
    var allUser : MutableLiveData<List<User>>

    init {
        allUser = MutableLiveData()
    }

    fun verifyUser(email : String, password : String) : LiveData<User> = NoteDatabase.getInstance((getApplication()))!!.userDao().verifyUser(email, password)

    fun insertUser(user : User){
        GlobalScope.async {
            val userDAO = NoteDatabase.getInstance(getApplication())?.userDao()!!
            userDAO.insertUser(user)
        }
    }
}