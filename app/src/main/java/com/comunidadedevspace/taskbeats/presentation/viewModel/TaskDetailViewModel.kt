package com.comunidadedevspace.taskbeats.presentation.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.comunidadedevspace.taskbeats.TaskBeatsApplication
import com.comunidadedevspace.taskbeats.data.local.Task
import com.comunidadedevspace.taskbeats.data.local.TaskDto
import com.comunidadedevspace.taskbeats.presentation.ActionType
import com.comunidadedevspace.taskbeats.presentation.TaskAction
import kotlinx.coroutines.launch

class TaskDetailViewModel(
    private val taskDto: TaskDto
) : ViewModel() {

    val taskListLiveData: LiveData<List<Task>> = taskDto.getAll()

    fun execute(taskAction: TaskAction) {
        when (taskAction.actionType) {
            ActionType.CREATE.name -> insertIntoDatabase(taskAction.task!!)
            ActionType.DELETE.name -> deleteIntoTask(taskAction.task!!)
            ActionType.UPDATE.name -> updateIntoTask(taskAction.task!!)
        }
    }

    private fun deleteAllTask() {
        viewModelScope.launch {
            taskDto.deleteAll()
        }
    }

    //Inserindo uma tarefa no banco de dados
    private fun insertIntoDatabase(task: Task) {
        viewModelScope.launch{
            taskDto.insert(task)
        }
    }

    //Alterando tarefas
    private fun updateIntoTask(task: Task) {
        viewModelScope.launch{
            taskDto.update(task)
        }
    }

    //Deletando uma tarefa
    private fun deleteIntoTask(task: Task) {
        viewModelScope.launch {
            taskDto.delete(task)
        }
    }

    companion object {
        fun getVMFactory(application: Application): ViewModelProvider.Factory {
            val databaseInstance = (application as TaskBeatsApplication).getAppDatabase()

            val dao = databaseInstance.taskDto()

            val factory = object : ViewModelProvider.Factory {

                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TaskDetailViewModel(dao) as T
                }
            }
            return factory
        }
    }
}