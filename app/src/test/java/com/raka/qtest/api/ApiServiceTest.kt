package com.raka.qtest.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.raka.qtest.data.api.ApiService
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class ApiServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var apiService: ApiService
    private lateinit var mockWebSerVer: MockWebServer
    @Before
    fun createService() {
        mockWebSerVer = MockWebServer()
        mockWebSerVer.start()
        val client = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebSerVer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
    @After
    fun stopService() {
        mockWebSerVer.shutdown()
    }
    @Test
    fun getProductListTest_passCorrectPath() {
        runBlocking {
            //Arrange
            setResponseSuccess("response.json")
            //Act
            val resultResponse = apiService.getProductList().blockingGet()
            val request = mockWebSerVer.takeRequest()
            //Assert
            Assert.assertNotNull(resultResponse)
            Assert.assertThat(
                request.path,
                `is`("/b/5ee794370e966a7aa369eafd")
            )
        }
    }
    @Test
    fun loginTest_success_getCorrectResponse(){
        runBlocking {
            //Arrange
            setResponseSuccess("response.json")
            //Act
            val resultResponse = apiService.getProductList().blockingGet()
            val dataLoginResponse = resultResponse.status
            //Response
            Assert.assertThat("200", `is`(dataLoginResponse))
        }
    }
    private fun setResponseSuccess(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebSerVer.enqueue(mockResponse.setBody(source.readString(Charsets.UTF_8)))
    }
}