package com.abdhilabs.learnroomdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdhilabs.learnroomdb.R
import com.abdhilabs.learnroomdb.db.Todo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_todo.*

class TodoAdapter(
    private val listTodo: MutableList<Todo>,
    private val clickListener: (Todo) -> Unit
) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = listTodo.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTodo[position], clickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), LayoutContainer {
        override val containerView: View?
            get() = itemView

        private val title = tv_item_title as TextView
        private val deadline = tv_item_deadline as TextView
        private val desc = tv_item_desc as TextView

        fun bind(todo: Todo, clickListener: (Todo) -> Unit) {
            title.text = todo.title
            deadline.text = todo.deadline
            desc.text = todo.desc
            itemView.setOnClickListener { clickListener(todo) }
        }

    }
}