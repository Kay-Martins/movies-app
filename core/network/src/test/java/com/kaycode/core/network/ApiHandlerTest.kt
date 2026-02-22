package com.kaycode.core.network

import com.kaycode.core.network.handler.ApiHandler
import com.kaycode.core.network.retrofit.model.ApiResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class ApiHandlerTest {

    private lateinit var sut: ApiHandler

    @Before
    fun setup() {
        sut = ApiHandler()
    }

    @Test
    fun `when api call is successful, return success response`() = runTest {
        suspend fun testSuccessfulResponse(): Response<String> {
            delay(500L)
            return Response.success("success")
        }

        val result = sut.handleApi { testSuccessfulResponse() }
        assertTrue(result is ApiResponse.Success)
        assertEquals("success", (result as ApiResponse.Success).data)

    }

    @Test
    fun `when api call is unsuccessful, return correct error response`() = runTest {
        suspend fun testUnsuccessfulResponse(): Response<String> {
            delay(500L)
            return Response.error(404, "fail".toResponseBody())
        }

        val result = sut.handleApi { testUnsuccessfulResponse() }
        assertTrue(result is ApiResponse.Error)
        assertEquals(404, (result as ApiResponse.Error).code)
        assertEquals("fail", result.message)
    }

    @Test
    fun `when api call throws HttpException, return correct error response`() = runTest {
        suspend fun testHttpException(): Response<String> {
            delay(500L)
            throw HttpException(Response.error<String>(400, "exception".toResponseBody()))
        }

        val result = sut.handleApi { testHttpException() }
        assertTrue(result is ApiResponse.Error)
        assertEquals(400, (result as ApiResponse.Error).code)
        assertEquals("exception", result.message)

    }

    @Test
    fun `when api call throws other exception, returns correct exception response`() = runTest {
        suspend fun testOtherException(): Response<String> {
            delay(500L)
            throw Throwable("error")
        }

        val result = sut.handleApi { testOtherException() }
        assertTrue(result is ApiResponse.Exception)
        assertEquals("error", (result as ApiResponse.Exception).e.message)
    }

}