package com.example.sample.todos.detailed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sample.todos.api.TodosService
import com.example.sample.utils.ResourceTracker
import com.example.sample.utils.Status
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import javax.inject.Provider

class TodoFragmentViewModel(private val todosService: TodosService) : ViewModel() {
    val todosList = flow {
        try {
            emit(ResourceTracker.success(data = todosService.getAllTodos().listOfTodo))
        } catch (exception: Exception) {
            emit(
                ResourceTracker.error(
                    data = null,
                    message = exception.message ?: "Error Resource loading!"
                )
            )
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        ResourceTracker.loading(data = null)
    )

    companion object {
        @Suppress("UNCHECKED_CAST")
        class Factory @Inject constructor(private val todosService: Provider<TodosService>) :
            ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                require(modelClass == TodoFragmentViewModel::class.java)
                return TodoFragmentViewModel(todosService.get()) as T
            }

        }
    }

}