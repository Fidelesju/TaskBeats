package com.comunidadedevspace.taskbeats

import MainDispatcherRule
import com.comunidadedevspace.taskbeats.data.local.Task
import com.comunidadedevspace.taskbeats.data.local.TaskDto
import com.comunidadedevspace.taskbeats.presentation.ActionType
import com.comunidadedevspace.taskbeats.presentation.TaskAction
import com.comunidadedevspace.taskbeats.presentation.viewModel.TaskDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@Suppress("ANNOTATION_TARGETS_NON_EXISTENT_ACCESSOR")
@OptIn(ExperimentalCoroutinesApi::class)
class TaskDetailViewModelTest : TestWatcher() {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val taskDto: TaskDto = mock()

    private val underTest: TaskDetailViewModel by lazy {
        TaskDetailViewModel(
            taskDto
        )
    }

    @Test
    fun update_task() = runTest {
        val taskInput = Task(
            1,
            "Title",
            "Description"
        )

        val taskAction = TaskAction(
            task = taskInput,
            actionType = ActionType.UPDATE.name
        )

        underTest.execute(taskAction)
        //Then
        verify(taskDto).update(taskInput)
    }

    @Test
    fun delete_task() = runTest {
        val taskInput = Task(
            1,
            "Title",
            "Description"
        )

        val taskAction = TaskAction(
            task = taskInput,
            actionType = ActionType.DELETE.name
        )

        underTest.execute(taskAction)
        //Then
        verify(taskDto).delete(taskInput)
    }

    @Test
    fun insert_task() = runTest {
        val taskInput = Task(
            1,
            "Title",
            "Description"
        )

        val taskAction = TaskAction(
            task = taskInput,
            actionType = ActionType.CREATE.name
        )

        underTest.execute(taskAction)
        //Then
        verify(taskDto).insert(taskInput)
    }

}


