package com.example.tmdbdemo.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.tmdbdemo.TMDBApplication
import com.example.tmdbdemo.di.components.ActivityComponent
import com.example.tmdbdemo.di.components.DaggerActivityComponent
import com.example.tmdbdemo.di.modules.ActivityModule
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependency(buildDependency())
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        setupViews(savedInstanceState)
        setupObservers()
        viewModel.onCreate()
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

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            showMessage(it)
        })
    }

    private fun showMessage(it: String?) {
        Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
    }

    abstract fun setupViews(savedInstanceState: Bundle?)

    abstract fun getLayoutResource(): Int
}