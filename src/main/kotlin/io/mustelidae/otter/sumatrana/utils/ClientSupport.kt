package io.mustelidae.otter.sumatrana.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.mustelidae.otter.sumatrana.api.common.Error
import io.mustelidae.otter.sumatrana.api.common.ErrorCode
import io.mustelidae.otter.sumatrana.api.config.CommunicationException
import io.mustelidae.otter.sumatrana.api.config.GlobalErrorFormat
import org.apache.hc.client5.http.classic.methods.HttpDelete
import org.apache.hc.client5.http.classic.methods.HttpGet
import org.apache.hc.client5.http.classic.methods.HttpPatch
import org.apache.hc.client5.http.classic.methods.HttpPost
import org.apache.hc.client5.http.classic.methods.HttpPut
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse
import org.apache.hc.core5.http.io.entity.EntityUtils
import org.apache.hc.core5.http.io.entity.StringEntity
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import java.nio.charset.Charset

open class ClientSupport(
    private val objectMapper: ObjectMapper,
    private val writeLog: Boolean,
    private val log: Logger
) {

    private fun <T> T.toJson(): String = objectMapper.writeValueAsString(this)

    fun CloseableHttpClient.post(url: String, headers: List<Pair<String, Any>>, body: Any? = null): CloseableHttpResponse {
        val post = HttpPost(url).apply {
            body?.let {
                entity = StringEntity(it.toJson(), Charsets.UTF_8)
            }

            headers.forEach {
                addHeader(it.first, it.second)
            }
        }

        return this.execute(post)
    }

    fun CloseableHttpClient.put(url: String, headers: List<Pair<String, Any>>, body: Any? = null): CloseableHttpResponse {
        val put = HttpPut(url).apply {
            body?.let {
                entity = StringEntity(it.toJson(), Charsets.UTF_8)
            }

            headers.forEach {
                addHeader(it.first, it.second)
            }
        }
        return this.execute(put)
    }

    fun CloseableHttpClient.patch(url: String, headers: List<Pair<String, Any>>, body: Any? = null): CloseableHttpResponse {

        val patch = HttpPatch(url).apply {
            body?.let {
                entity = StringEntity(it.toJson(), Charsets.UTF_8)
            }

            headers.forEach {
                addHeader(it.first, it.second)
            }
        }

        return this.execute(patch)
    }

    fun CloseableHttpClient.delete(url: String, headers: List<Pair<String, Any>>, params: List<Pair<String, Any?>>? = null): CloseableHttpResponse {
        val queryString = params?.joinToString("&") { "${it.first}=${it.second}" }
        val uri = if (queryString.isNullOrBlank().not()) url + queryString?.let { "?$it" } else url
        val delete = HttpDelete(uri).apply {
            headers.forEach {
                addHeader(it.first, it.second)
            }
        }

        return this.execute(delete)
    }

    fun CloseableHttpClient.deleteUsingRequestBody(url: String, headers: List<Pair<String, Any>>, body: Any? = null): CloseableHttpResponse {
        val delete = HttpDelete(url).apply {
            body?.let {
                entity = StringEntity(it.toJson(), Charsets.UTF_8)
            }

            headers.forEach {
                addHeader(it.first, it.second)
            }
        }

        return this.execute(delete)
    }

    fun CloseableHttpClient.get(url: String, headers: List<Pair<String, Any>>, params: List<Pair<String, Any?>>? = null): CloseableHttpResponse {
        val queryString = params?.joinToString("&") { "${it.first}=${it.second}" }
        val uri = if (queryString.isNullOrBlank().not()) url + queryString?.let { "?$it" } else url
        val get = HttpGet(uri).apply {
            headers.forEach {
                addHeader(it.first, it.second)
            }
        }

        return this.execute(get)
    }

    fun CloseableHttpResponse.orElseThrow(): String {
        if (this.isOK() && this.entity == null) {
            return ""
        }

        val response = EntityUtils.toString(this.entity, Charset.defaultCharset())
        writeLog(response)

        if (writeLog && this.isOK().not()) {
            val error = if (response.isNullOrEmpty()) {
                Error(ErrorCode.C000, this.reasonPhrase)
            } else {
                val globalErrorFormat = objectMapper.readValue<GlobalErrorFormat>(response)
                Error(ErrorCode.C000, globalErrorFormat.message).apply {
                    refCode = globalErrorFormat.refCode
                    causeBy = mapOf(
                        "type" to globalErrorFormat.type,
                        "description" to globalErrorFormat.description
                    )
                }
            }

            throw CommunicationException(error)
        }

        return response
    }

    private fun CloseableHttpResponse.isOK(): Boolean {
        return (HttpStatus.valueOf(this.code).is2xxSuccessful)
    }

    private fun writeLog(response: String) {
        if (writeLog) {
            log.error("-- error --\n$response")
        }
    }
}
