package com.raka.qtest.utils

import com.raka.myapplication.view.utils.Util
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.*
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertThat
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito.*
import org.mockito.Mockito.verify
import org.hamcrest.CoreMatchers.`is`

@RunWith(MockitoJUnitRunner::class)
class UtilTest {
    @Test
    fun isInternetAvailable_success_returnTrue(){
        runBlocking {
            val result = Util.isInternetAvailable()
            assertThat(true, `is`(result))
        }
    }
    @Test
    fun isInternetAvailable_failure_returnFalse(){
        runBlocking {
            val result = Util.isInternetAvailable()
            assertThat(false, `is`(result))
        }
    }
}