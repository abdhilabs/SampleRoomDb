package com.abdhilabs.learnroomdb

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdhilabs.learnroomdb.BaseApp.Companion.db
import com.abdhilabs.learnroomdb.adapter.TodoAdapter
import com.abdhilabs.learnroomdb.db.Todo
import com.abdhilabs.learnroomdb.utils.Const.DATA_TASK
import com.abdhilabs.learnroomdb.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_button.view.*

class MainActivity : AppCompatActivity() {

    private var listTodo: MutableList<Todo> = mutableListOf()
    lateinit var adapterTodo: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showList()

        btn_add_todo.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }

    private fun showList() {
        listTodo.clear()
        listTodo.addAll(db.taskDao().getAll())
        adapterTodo = TodoAdapter(listTodo) { todo: Todo -> onClick(todo) }
        rv_list_todo.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterTodo
        }
    }

    private fun onClick(todo: Todo) {
        val dialogBuilder = AlertDialog.Builder(this).create()
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.layout_button, null)

        dialogView.btn_dialog_update_task.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(DATA_TASK, todo)
            startActivity(intent)
            dialogBuilder.dismiss()
        }

        dialogView.btn_dialog_delete.setOnClickListener {
            db.taskDao().deleteTask(todo)
            "Kamu berhasil hapus si ${todo.title}".toast(this)
            showList()
            dialogBuilder.dismiss()
        }

        dialogBuilder.setView(dialogView)
        dialogBuilder.show()
    }

}
