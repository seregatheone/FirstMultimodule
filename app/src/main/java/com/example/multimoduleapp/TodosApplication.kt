package com.example.multimoduleapp

import android.app.Application
import com.example.multimoduleapp.di.AppComponent
import com.example.multimoduleapp.di.DaggerAppComponent
import com.example.sample.todos.detailed.TodosDetailedDeps
import com.example.sample.todos.detailed.TodosDeteiledDepsProvider
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TodosApplication : Application(),TodosDeteiledDepsProvider{
    private val appComponent :AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
    override val deps: TodosDetailedDeps = appComponent
}