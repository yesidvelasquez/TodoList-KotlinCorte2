package com.example.todo_kotlin

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemLongClickListener
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var tasks: java.util.ArrayList<String>? = null
    var tasksAdapter: ArrayAdapter<String>? = null
    var listView: ListView? = null
    var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.listView)
        button = findViewById<Button>(R.id.button)

        button?.setOnClickListener(View.OnClickListener { view -> addTask(view) })

        tasks = ArrayList<String>()
        tasksAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks!!)
        listView?.adapter = tasksAdapter
        setUpListViewListener()
    }

    private fun setUpListViewListener() {

        listView?.setOnItemLongClickListener(OnItemLongClickListener { adapterView, view, i, l ->
            val context = applicationContext
            Toast.makeText(context, "Task removed.", Toast.LENGTH_SHORT).show()
            tasks?.removeAt(i)
            tasksAdapter?.notifyDataSetChanged()
            true
        })
    }

    private fun addTask(view: View) {
        val text = findViewById<EditText>(R.id.taskText)
        val taskText = text.text.toString()
        if (taskText != "") {
            tasksAdapter?.add(taskText)
            text.setText("")
        } else {
            Toast.makeText(applicationContext, "Please, write your task...", Toast.LENGTH_LONG)
                .show()
        }
    }
}