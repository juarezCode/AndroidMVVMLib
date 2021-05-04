package juarez.roberto.mylibrary.utils.viewmodelfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import juarez.roberto.mylibrary.db.MasterDatabase
import juarez.roberto.mylibrary.viewmodel.HomeViewModel

class HomeViewModelFactory(private val context: Context, private val db: MasterDatabase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(context, db) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}