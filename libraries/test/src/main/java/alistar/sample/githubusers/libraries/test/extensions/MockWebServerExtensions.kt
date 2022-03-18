package alistar.sample.githubusers.libraries.test.extensions

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

/**
 * Enqueues a response that will be used for the next API call
 * @param fileName: the name of the response file located in the api-response folder
 * @param code: http response code
 */
fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(StandardCharsets.UTF_8))
        )
    }
}

/**
 * This method gives us a mocked response for using it in the MockWebServer dispatcher
 * @param fileName: the name of the response file located in the api-response folder
 * @param code: http response code
 * @return mocked response
 */
fun MockWebServer.mockResponse(fileName: String, code: Int): MockResponse {
    val inputStream = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")

    val source = inputStream?.let { inputStream.source().buffer() }
    return MockResponse()
        .setResponseCode(code)
        .setBody(source?.readString(StandardCharsets.UTF_8) ?: "no response")
}
