package com.example.sample.todos.detailed

import android.app.Application
import androidx.lifecycle.AndroidViewModel

internal class TodosDetailedComponentViewModel(application: Application) : AndroidViewModel(application) {
    val todosDetailedComponent: TodosDetailedComponent by lazy {
        DaggerTodosDetailedComponent.builder()
            .deps(application.todosDeteiledDepsProvider.deps)
            .build()
    }
}