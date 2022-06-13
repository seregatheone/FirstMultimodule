package com.example.sample.todos.api

import android.annotation.SuppressLint
import com.example.sample.todos.api.models.TodosList
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Retrofit
import retrofit2.http.GET


interface TodosService{
    @GET("/todos")
    suspend fun getAllTodos():TodosList


}
fun TodosService():TodosService{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
        .build()
    return retrofit.create(TodosService::class.java)
}