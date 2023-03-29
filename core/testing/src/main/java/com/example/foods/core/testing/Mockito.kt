package com.example.foods.core.testing

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

fun <T> given(clas: T): OngoingStubbing<T> = Mockito.`when`(clas)
