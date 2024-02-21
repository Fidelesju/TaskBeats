package com.comunidadedevspace.taskbeats.presentation.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.comunidadedevspace.taskbeats.TaskBeatsApplication
import com.comunidadedevspace.taskbeats.data.local.Task
import com.comunidadedevspace.taskbeats.data.local.TaskDto

class TaskListViewModel(
    taskDto: TaskDto
) : ViewModel() {

    val taskListLiveData: LiveData<List<Task>> = taskDto.getAll()


    companion object {
        fun create(application: Application): TaskListViewModel {
            val databaseInstance = (application as TaskBeatsApplication).getAppDatabase()
            val dao = databaseInstance.taskDto()
            return TaskListViewModel(dao)
        }
    }
}