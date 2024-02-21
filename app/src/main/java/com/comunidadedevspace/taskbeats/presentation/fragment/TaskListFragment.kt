package com.comunidadedevspace.taskbeats.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.comunidadedevspace.taskbeats.R
import com.comunidadedevspace.taskbeats.data.local.Task
import com.comunidadedevspace.taskbeats.presentation.activity.TaskDetailActivity
import com.comunidadedevspace.taskbeats.presentation.adapter.TaskListAdapter
import com.comunidadedevspace.taskbeats.presentation.viewModel.TaskListViewModel

class TaskListFragment : Fragment() {

    //Conteiner de imagem em estado de vazio
    private lateinit var ctnContent: LinearLayout

    //Adapter
    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter(::openTaskListDetail)
    }

    //View Model
    private val viewModel: TaskListViewModel by lazy {
        TaskListViewModel.create(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ctnContent = view.findViewById(R.id.ctn_content)
        val rvTaskList: RecyclerView = view.findViewById(R.id.rv_task_list)

        rvTaskList.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        listFromDatabase()
    }
    //Listando as tarefas na tela
    private fun listFromDatabase() {

        //Observer
        val listObserver = Observer<List<Task>> {
            if (it.isEmpty()) {
                ctnContent.visibility = View.VISIBLE
            } else {
                ctnContent.visibility = View.GONE
            }
            adapter.submitList(it)
        }

        //LiveData
        viewModel.taskListLiveData.observe(this, listObserver)

    }

    //Evento de clique vindo do botão (nao contem tarefa)
    private fun openTaskListDetail(task: Task) {
        val intent = TaskDetailActivity.start(requireContext(), task)
        requireActivity().startActivity(intent)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TaskListFragment().apply {

            }
    }
}