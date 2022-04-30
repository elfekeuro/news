package com.example.news.extension

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun <T> Activity?.intentToAnotherActivity(
    tClass: Class<T>,
    bundle: Bundle? = null,
    finished: Boolean = false
) {
    val intent = Intent(this, tClass)

    if (bundle != null)
        intent.putExtras(bundle)
    this?.startActivity(intent)
}

fun Activity.isGrantedPermission(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_DENIED
}

fun Activity.newRequestPermissions(permission: String, requestCode: Int) {
    ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
}

fun Activity.toast(message: String, toastType: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, toastType).show()
}
