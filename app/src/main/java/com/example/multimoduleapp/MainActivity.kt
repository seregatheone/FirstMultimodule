package com.example.multimoduleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentOnAttachListener
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.sample.todos.detailed.TodoFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        val fragmentManager = supportFragmentManager
        if(fragmentManager.findFragmentById(R.id.fragmentContainerView2)==null){
            fragmentManager.commit{
                add(R.id.fragmentContainerView2,TodoFragment())
            }
        }
        super.onCreate(savedInstanceState)
    }

    companion object{
        private const val FRAGMENT_TODOS = "todos"
    }
}