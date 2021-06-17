package com.mysub2e.capstoneproject2.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
