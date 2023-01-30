package io.mustelidae.otter.sumatrana.utils

import io.mustelidae.otter.sumatrana.api.config.AppEnvironment
import org.apache.hc.client5.http.config.RequestConfig
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.apache.hc.client5.http.impl.classic.HttpClients
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder
import org.apache.hc.core5.util.TimeValue
import java.util.concurrent.TimeUnit

object RestClient {

    fun new(connInfo: AppEnvironment.ConnInfo): CloseableHttpClient {
        val manager = PoolingHttpClientConnectionManagerBuilder.create()
            .setMaxConnPerRoute(connInfo.perRoute)
            .setMaxConnTotal(connInfo.connTotal)
            .setConnectionTimeToLive(TimeValue.ofSeconds(connInfo.connLiveDuration))
            .build()

        return HttpClients.custom()
            .setConnectionManager(manager)
            .setDefaultRequestConfig(
                RequestConfig.custom()
                    .setConnectTimeout(connInfo.timeout, TimeUnit.SECONDS)
                    .setResponseTimeout(connInfo.timeout * 2, TimeUnit.SECONDS)
                    .build()
            )
            .build()
    }
}
