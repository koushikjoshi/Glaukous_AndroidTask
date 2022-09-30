package com.koushikjoshi.glaukous_androidtask

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
import java.util.*
import java.util.Collections.unmodifiableList


class TodoAdapter: RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

//    Inner class that returns RecyclerView's ViewHolder

    inner class TodoViewHolder(val binding : RecyclerViewBgBinding) : RecyclerView.ViewHolder(binding.root)



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

//        if sequenceID is the least among status 0, only then add elements to recyclerview

        var min = Integer.MAX_VALUE
        var pos = -1
        var i = 0
        todos.forEach{
            if(it.status==0 && it.sequenceID<min){
                min = it.sequenceID
                pos = i
            }
            i++
        }

        if(position != pos){

        holder.binding.apply {
            val todo = todos[position]
            recyclerItemCodeTextView.text = todo.itemCode
            recyclerLocationTextView.text = todo.locationID
            recyclerPickedTextView.text = todo.quantityPicked.toString()
            recyclerQuantityTextView.text = todo.quantityToBePicked.toString()




//          if status is 0 and quantityPicked < quantityToBePicked, then change the background color

            var recyclerDrawable: Drawable = recyclerCardView.getBackground()
            recyclerDrawable = DrawableCompat.wrap(recyclerDrawable!!)
            if(todo.status !=0 && todo.quantityPicked < todo.quantityToBePicked){
                DrawableCompat.setTint(recyclerDrawable, Color.parseColor("#FEF3E9"))
                recyclerCardView.background = recyclerDrawable
            }
            else if(todo.status !=0 && todo.quantityPicked >= todo.quantityToBePicked){
                DrawableCompat.setTint(recyclerDrawable, Color.parseColor("#EAFEE9"))
                recyclerCardView.background = recyclerDrawable
            }
            else if(todo.status == 0){
                DrawableCompat.setTint(recyclerDrawable, Color.parseColor("#F3F8FF"))
                recyclerCardView.background = recyclerDrawable

            }

        }
        }
        else{
//            if sequenceID is equal to pos, remove the element of recyclerView

            holder.binding.recyclerCardView.visibility = View.GONE
        }



//        todos = todos.sortedBy {
//            it.status
//        }

        todos = todos.sortedWith(compareBy<Item>{it.status}.thenByDescending{it.quantityToBePicked}) as MutableList<Item>

        var modifiableList: List<Item> = todos

        var statPos = 0
        while(todos[statPos].status==0){
            statPos++
        }

        var lastPosVar = 0
        
//        var newList = todos.subList(statPos, todos.size-1).sortedWith(
//            compareByDescending { it.status })
//        var j = 0
//        for(i in statPos..todos.size-1){
//            todos[i] = newList[j]
//            j++
//        }

    }

}