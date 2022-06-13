package com.example.multimoduleapp.di

import android.app.Application
import com.example.sample.todos.api.TodosService
import com.example.sample.todos.detailed.TodosDetailedDeps
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent: TodosDetailedDeps{
    override val todosService: TodosService

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application):Builder
        fun build():AppComponent
    }
}
@Module
class AppModule{
    @Provides
    @AppScope
    fun provideTodosService():TodosService = TodosService()
}

@Scope
annotation class AppScope