package com.example.sample.todos.detailed

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.sample.todos.api.models.Todo
import com.example.sample.todos.detailed.databinding.TodoFragmentBinding
import com.example.sample.todos.detailed.databinding.TodoItemBinding

class TodoAdapter(
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var values = emptyList<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
            )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.itemView.findViewById<TextView>(R.id.item_number).setText(item.id)
        holder.itemView.findViewById<TextView>(R.id.content).text = item.title
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    fun setNewValues(listOfTodos : List<Todo>){
        values = listOfTodos
        notifyDataSetChanged()
    }

}