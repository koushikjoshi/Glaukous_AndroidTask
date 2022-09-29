package com.koushikjoshi.glaukous_androidtask

import android.R.attr.button
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.koushikjoshi.glaukous_androidtask.databinding.RecyclerViewBgBinding


class TodoAdapter: RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

//    Inner class that returns RecyclerView's ViewHolder
    inner class TodoViewHolder(val binding: RecyclerViewBgBinding) : RecyclerView.ViewHolder(binding.root)

//    DiffUtil for faster loading of RecyclerView
    private val diffCallback = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

//     convert diffutil list into todos with get and set method

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

//        if sequenceID is not equal to 1, only then add elements to recyclerview

        if(todos[position].sequenceID!=1){

        holder.binding.apply {
            val todo = todos[position]
            recyclerItemCodeTextView.text = todo.itemCode
            recyclerLocationTextView.text = todo.locationID
            recyclerPickedTextView.text = todo.quantityPicked.toString()
            recyclerQuantityTextView.text = todo.quantityToBePicked.toString()

//          if quantityPicked < quantityToBePicked, then change the background color

            var recyclerDrawable: Drawable = recyclerCardView.getBackground()
            recyclerDrawable = DrawableCompat.wrap(recyclerDrawable!!)
            if(todo.quantityPicked < todo.quantityToBePicked){
                DrawableCompat.setTint(recyclerDrawable, Color.parseColor("#FEF3E9"))
                recyclerCardView.background = recyclerDrawable
            }

        }
        }
        else{
//            if sequenceID is equal to 1, remove 1st element of recyclerView

            holder.binding.recyclerCardView.visibility = View.GONE
        }
    }

}