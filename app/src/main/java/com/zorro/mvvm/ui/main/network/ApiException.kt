package com.zorro.mvvm.ui.main.network

/**
 * Created by pangli on 2019/9/27.
 */
class ApiException : RuntimeException {

    private var code: Int? = null

    constructor(throwable: Throwable, code: Int) : super(throwable) {
        this.code = code
    }

    constructor(message: String) : super(Throwable(message))
}