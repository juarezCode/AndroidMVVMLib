package juarez.roberto.mylibrary.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import juarez.roberto.mylibrary.adapters.UserListAdapter
import juarez.roberto.mylibrary.databinding.ActivityHomeBinding
import juarez.roberto.mylibrary.db.MasterDatabase
import juarez.roberto.mylibrary.utils.viewmodelfactory.HomeViewModelFactory
import juarez.roberto.mylibrary.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModelFactory: HomeViewModelFactory
    private val userListAdapter = UserListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        binding.btnRetry.setOnClickListener {
            binding.btnRetry.isEnabled = false
            binding.cardError.visibility = View.GONE
            viewModel.getUsers()
        }

        viewModel.users.observe(this, { users ->
            Log.i(TAG, users.toString())
            binding.btnRetry.isEnabled = true
            binding.cardError.visibility = View.GONE
            binding.recyclerUsers.visibility = View.VISIBLE
            userListAdapter.updateUsers(users)
        })

        viewModel.isLoading.observe(this, {
            binding.loading.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.error.observe(this, {
            Log.i(TAG, it.toString())
            binding.btnRetry.isEnabled = true
            binding.txtError.text = it
            binding.cardError.visibility = View.VISIBLE
        })
    }

    private fun init() {
        viewModelFactory = HomeViewModelFactory(application, MasterDatabase(this))
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        binding.cardError.visibility = View.GONE
        binding.recyclerUsers.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = userListAdapter
            visibility = View.GONE
        }

    }
}