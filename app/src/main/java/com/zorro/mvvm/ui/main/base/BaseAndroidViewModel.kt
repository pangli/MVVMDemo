package com.zorro.mvvm.ui.main.base

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.zorro.mvvm.ui.main.network.ExceptionHandle
import kotlinx.coroutines.launch

/**
 * Created by pangli on 2019/9/27.
 */
abstract class BaseAndroidViewModel(application: Application) : AndroidViewModel(application) {
    protected fun run(success: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                success()
            } catch (e: Throwable) {
                error(e)
                Toast.makeText(
                    getApplication(),
                    ExceptionHandle.handleException(e),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
}