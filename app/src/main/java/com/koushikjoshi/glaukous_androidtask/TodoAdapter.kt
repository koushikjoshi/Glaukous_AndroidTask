package com.koushikjoshi.glaukous_androidtask

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.koushikjoshi.glaukous_androidtask.databinding.FragmentHomeBinding
import com.koushikjoshi.glaukous_androidtask.databinding.RecyclerViewBgBinding

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: RecyclerViewBgBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var todos : List<Item>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }

    override fun getItemCount() = todos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(RecyclerViewBgBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            recyclerItemCodeTextView.text = todo.itemCode
            recyclerLocationTextView.text = todo.locationID
            recyclerPickedTextView.text = todo.quantityPicked.toString()
            recyclerQuantityTextView.text = todo.quantityToBePicked.toString()

        }
    }

}