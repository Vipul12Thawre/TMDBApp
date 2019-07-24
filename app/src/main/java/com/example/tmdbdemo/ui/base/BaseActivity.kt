package com.example.tmdbdemo.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tmdbdemo.TMDBApplication
import com.example.tmdbdemo.di.components.ActivityComponent
import com.example.tmdbdemo.di.components.DaggerActivityComponent
import com.example.tmdbdemo.di.modules.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency(buildDependency())
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        setupViews(savedInstanceState)
        onCreate()
        setupObservers()
    }

    abstract fun injectDependency(component: ActivityComponent)

    private fun buildDependency(): ActivityComponent {
        return DaggerActivityComponent.builder()
            .applicationComponent((application as TMDBApplication).component)
            .activityModule(
                ActivityModule(
                    this
                )
            )
            .build()

    }

    abstract fun onCreate()

    abstract fun setupObservers()

    abstract fun setupViews(savedInstanceState: Bundle?)

    abstract fun getLayoutResource(): Int
}