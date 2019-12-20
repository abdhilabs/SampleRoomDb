package com.abdhilabs.learnroomdb

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abdhilabs.learnroomdb.BaseApp.Companion.db
import com.abdhilabs.learnroomdb.db.Todo
import com.abdhilabs.learnroomdb.utils.Const.DATA_TASK
import com.abdhilabs.learnroomdb.utils.toast
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {

    private var todo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        todo = intent.getParcelableExtra(DATA_TASK)

        if (todo != null) {
            btnUpdateTask.visibility = View.VISIBLE
            btnAddTask.visibility = View.GONE
        }

        getDataUpdate(todo)

        btnAddTask.setOnClickListener {
            addTodo()
        }

        btnUpdateTask.setOnClickListener {
            updateTodo()
        }
    }

    private fun getDataUpdate(data: Todo?) {
        et_add_title.setText(data?.title)
        et_add_deadline.setText(data?.deadline)
        et_add_desc.setText(data?.desc)

    }

    private fun updateTodo() {
        val id = todo!!.id
        val title = et_add_title.text.toString()
        val deadline = et_add_deadline.text.toString()
        val desc = et_add_desc.text.toString()

        db.taskDao().update(id, title, deadline, desc)
        "Success Update Task".toast(this)
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }


    private fun addTodo() {
        val title = et_add_title.text.toString()
        val deadline = et_add_deadline.text.toString()
        val desc = et_add_desc.text.toString()

        if (title.isNotEmpty() && deadline.isNotEmpty() && desc.isNotEmpty()) {
            val todo = Todo(title, deadline, desc)
            db.taskDao().insertAll(todo)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Isi data terlebih dahulu", Toast.LENGTH_SHORT).show()
        }
    }
}
