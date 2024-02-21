package com.comunidadedevspace.taskbeats

import com.comunidadedevspace.taskbeats.data.local.TaskDto
import com.comunidadedevspace.taskbeats.presentation.viewModel.TaskListViewModel
import org.mockito.kotlin.mock

class TaskListViewModelTest {

    private val taskDto: TaskDto = mock()
    private val underTest: TaskListViewModel by lazy {
        TaskListViewModel(taskDto)
    }

}