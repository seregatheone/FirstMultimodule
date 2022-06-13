package com.example.sample.todos.detailed

import android.app.Application
import android.content.Context
import com.example.sample.todos.api.TodosService
import dagger.Component
import dagger.Module

internal annotation class TodosDetailedScope

@TodosDetailedScope
@Component(dependencies = [TodosDetailedDeps::class], modules = [TodosDetailedModule::class ])
interface TodosDetailedComponent {
    fun inject(todoFragment: TodoFragment)

    @Component.Builder
    interface Builder{
        fun deps(todosDetailedDeps: TodosDetailedDeps):Builder
        fun build():TodosDetailedComponent
    }
}

@Module
internal class TodosDetailedModule

interface TodosDeteiledDepsProvider{
    val deps : TodosDetailedDeps
}

interface TodosDetailedDeps  {
    val todosService : TodosService
}

val Context.todosDeteiledDepsProvider : TodosDeteiledDepsProvider
    get() =when(this){
        is TodosDeteiledDepsProvider -> this
        is Application -> error("app must impl TodosDeteiledDepsProvider")
        else -> applicationContext.todosDeteiledDepsProvider
    }