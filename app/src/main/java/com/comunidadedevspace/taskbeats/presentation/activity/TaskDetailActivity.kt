package com.comunidadedevspace.taskbeats.presentation.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.local.Task
import com.comunidadedevspace.taskbeats.presentation.ActionType
import com.comunidadedevspace.taskbeats.presentation.TaskAction
import com.comunidadedevspace.taskbeats.presentation.viewModel.TaskDetailViewModel
import com.google.android.material.snackbar.Snackbar

class TaskDetailActivity : AppCompatActivity() {

    private var task: Task? = null

    private val viewModel: TaskDetailViewModel by viewModels {
        TaskDetailViewModel.getVMFactory(
            application
        )
    }

    companion object {
        private const val TASK_DETAIL_EXTRA = "task.extra.detail"

        //metodo para iniciar uma tarefa
        fun start(context: Context, task: Task?): Intent {
            val intent = Intent(context, TaskDetailActivity::class.java)
                .apply {
                    putExtra(TASK_DETAIL_EXTRA, task)
                }
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        var edtTitle: EditText = findViewById(R.id.edt_title)
        var edtDescription: EditText = findViewById(R.id.edt_description)
        val btnApply: Button = findViewById(R.id.btn_apply)

        task = intent.getSerializableExtra(TASK_DETAIL_EXTRA) as? Task

        if (task != null) {
            edtTitle.setText(task!!.title)
            edtDescription.setText(task!!.description)
        }

        btnApply.setOnClickListener {
            val title = edtTitle.text.toString()
            val description = edtDescription.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
                if (task == null) {
                    addOrUpdateTask(0, title, description, ActionType.CREATE)
                } else {
                    addOrUpdateTask(task!!.id, title, description, ActionType.UPDATE)
                }
            } else {
                showMessage(it, "Fields are required")
                println("Deu erro setOnClickListener")
            }
        }
    }

    private fun addOrUpdateTask(
        id: Int,
        title: String,
        description: String,
        actionType: ActionType
    ) {
        val task = Task(id, title, description)
        performAction(task, actionType)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_task_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_task -> {
                if (task != null) {
                    performAction(task!!, ActionType.DELETE)
                } else {
                    println("Deu erro onOptionsItemSelected")
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }

    private fun performAction(task: Task, actionType: ActionType) {

        val taskAction = TaskAction(task, actionType.name)
        viewModel.execute(taskAction)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}