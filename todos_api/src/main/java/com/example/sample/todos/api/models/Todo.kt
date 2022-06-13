package com.example.sample.todos.api.models
import com.google.gson.annotations.SerializedName

data class TodosList(
    val listOfTodo : List<Todo>
)

data class Todo(
    @SerializedName("completed")
    val completed: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)