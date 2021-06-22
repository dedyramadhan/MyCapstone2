package com.capstoneproject2.core.utils

import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class AppExecutors @VisibleForTesting constructor(private val diskIO: Executor){

    fun diskIO(): Executor = diskIO

    @Inject
    constructor(): this(
        Executors.newSingleThreadExecutor()
    )

}