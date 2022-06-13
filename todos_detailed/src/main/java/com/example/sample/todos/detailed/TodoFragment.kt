package com.example.sample.todos.detailed

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.sample.todos.detailed.databinding.TodoFragmentBinding
import com.example.sample.utils.Status
import dagger.Lazy
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class TodoFragment : Fragment() {

    @Inject
    internal lateinit var todosViewModelFactory : Lazy<TodoFragmentViewModel.Companion.Factory>

    private var _binding:TodoFragmentBinding? = null
    private val binding get() = _binding!!

    private val todosViewModel:TodoFragmentViewModel by viewModels{todosViewModelFactory.get() }
    private var adapter : TodoAdapter? = null

    private val componentViewModel : TodosDetailedComponentViewModel by viewModels()

    override fun onAttach(activity: Activity) {
        componentViewModel.todosDetailedComponent.inject(this)
        super.onAttach(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TodoFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val todosAdapter = TodoAdapter()
        this.adapter = todosAdapter


        val layoutManager = LinearLayoutManager(requireContext())
        val recyclerView = binding.recView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager

        todosViewModel.todosList.onEach {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recView.visibility = View.VISIBLE
                        resource.data?.let { users -> adapter!!.setNewValues(users) }
                    }
                    Status.ERROR -> {
                        binding.recView.visibility = View.VISIBLE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.recView.visibility = View.GONE
                    }
                    else -> {throw Exception("Wrong Resource management")}
                }
            }
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        adapter = null
        _binding = null
        super.onDestroy()
    }
}