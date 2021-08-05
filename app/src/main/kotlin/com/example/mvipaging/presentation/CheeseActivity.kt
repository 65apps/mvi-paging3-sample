package com.example.mvipaging.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.arkivanov.mvikotlin.core.lifecycle.asMviLifecycle
import com.example.mvipaging.R

class CheeseActivity : AppCompatActivity() {
    private val binder: CheeseBinder by viewModels()
    private val view by lazy {
        CheeseViewImpl(findViewById(R.id.root))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheese)
        binder.onViewCreated(view, lifecycle.asMviLifecycle())
    }
}
