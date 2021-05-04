package juarez.roberto.mylibrary.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import juarez.roberto.mylibrary.models.User
import juarez.roberto.mylibrary.api.WebService
import juarez.roberto.mylibrary.db.MasterDatabase
import juarez.roberto.mylibrary.utils.Network
import juarez.roberto.mylibrary.utils.SetError
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class  HomeViewModel(context: Context, private val db: MasterDatabase) : ViewModel() {
    val users = MutableLiveData<List<User>>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val context = context
    private val TAG = "HomeViewModel ->"

    init {
        getUsers()
    }

    fun getUsers() = viewModelScope.launch {
        Log.i(TAG, "gettingUsers")
        isLoading.value = true
        val isOnline = Network.isOnline(context)

        if (!isOnline) {
            error.value = SetError.code(0)
            isLoading.value = false
        } else {
            try {
                val response = WebService.service().getUsers()
                isLoading.value = false
                if (response.isSuccessful) {
                    saveUsersDB(response.body()!!)
                    users.value = response.body()
                } else {
                    error.value = SetError.code(response.code())
                }

            } catch (e: Exception) {
                isLoading.value = false
                error.value = e.message
            }
        }
    }

    private suspend fun saveUsersDB(users: List<User>) = viewModelScope.launch {
        db.getUserDao().insert(users)
    }
}