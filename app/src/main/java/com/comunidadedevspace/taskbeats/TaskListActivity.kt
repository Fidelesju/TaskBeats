package com.comunidadedevspace.taskbeats

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    //Kotlin
    private var taskList = arrayListOf(
        Task(0, title = "Academia", description = "Lorem ipsum dolor sit amet, consectetu"),
        Task(1, title = "Mercado", description = "Lorem ipsum dolor sit amet, consectetu")
    )

    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter(::onListItemClicked)
    }
    private lateinit var ctnContent: LinearLayout

    //Criando uma base de dados
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext, AppDataBase::class.java, "taskbeats-database"
        ).build()
    }

    //Iniciando uma base de dados
    private val dao by lazy {
        database.taskDto()
    }

    private val resultActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            //pegando resultado
            val data = result.data
            val taskAction = data?.getSerializableExtra(TASK_ACTION_RESULT) as TaskAction
            val task: Task = taskAction.task

            if (taskAction.actionType == ActionType.DELETE.name) {

                deleteIntoTask(task)
                showMessage(ctnContent, "Item deleted ${task.title}")
            }
            if (taskAction.actionType == ActionType.CREATE.name) {

                insertIntoDatabase(task)
                showMessage(ctnContent, "Item created ${task.title}")
            }
            if (taskAction.actionType == ActionType.UPDATE.name) {

                updateIntoTask(task)
                showMessage(ctnContent, "Item updated ${task.title}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        listFromDatabase()

        ctnContent = findViewById(R.id.ctn_content)

        val rvTaskList: RecyclerView = findViewById(R.id.rv_task_list)
        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        rvTaskList.adapter = adapter

        fab.setOnClickListener {
            openTaskListDetail(null)
        }
    }

    //Inserindo uma tarefa no banco de dados
    private fun insertIntoDatabase(task: Task) {
        CoroutineScope(IO).launch {
            dao.insert(task)
            listFromDatabase()
        }
    }

    private fun updateIntoTask(task: Task) {
        CoroutineScope(IO).launch {
            dao.update(task)
            listFromDatabase()
        }
    }

    //Deletando uma tarefa
    private fun deleteIntoTask(task: Task) {
        CoroutineScope(IO).launch {
            dao.delete(task)
            listFromDatabase()
        }
    }

    //Listando as tarefas na tela
    private fun listFromDatabase() {
        CoroutineScope(IO).launch {
            val myDatabaseList = dao.getAll()
            adapter.submitList(myDatabaseList)

            CoroutineScope(Main).launch {
                if (myDatabaseList.isEmpty()) {
                    ctnContent.visibility = View.VISIBLE
                } else {
                    ctnContent.visibility = View.GONE
                }
            }
        }
    }

    //Mostrando um Snackbar
    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    //Evento de clique vindo da lista (contem tarefa)
    private fun onListItemClicked(task: Task) {
        openTaskListDetail(task)
    }

    //Evento de clique vindo do botão (nao contem tarefa)
    private fun openTaskListDetail(task: Task? = null) {
        val intent = TaskDetailActivity.start(this, task)
        resultActivity.launch(intent)
    }
}

//CRUD
enum class ActionType {
    DELETE, UPDATE, CREATE
}

data class TaskAction(
    val task: Task, val actionType: String
) : Serializable

const val TASK_ACTION_RESULT = "TASK_ACTION_RESULT"