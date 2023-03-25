package com.example.common.testing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher

@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestDispatcher) :
    TestWatcher() {

    override fun starting(description: org.junit.runner.Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: org.junit.runner.Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
