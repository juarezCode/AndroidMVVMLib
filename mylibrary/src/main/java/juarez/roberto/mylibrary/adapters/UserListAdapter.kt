package juarez.roberto.mylibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import juarez.roberto.mylibrary.models.User
import juarez.roberto.mylibrary.databinding.ItemUserBinding

class UserListAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    fun updateUsers(newUsers: List<User>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(users[position]) {
                binding.txtId.text = id.toString()
                binding.txtName.text = name
            }
        }
    }

    override fun getItemCount() = users.size
}