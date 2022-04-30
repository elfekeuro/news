package com.example.news.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initialView()
    abstract fun observerData()
    abstract fun getViewBinding(): View

    var onRequestPermissions: ((
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getViewBinding())
        initialView()
        observerData()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissions?.invoke(requestCode, permissions, grantResults)
    }
}
