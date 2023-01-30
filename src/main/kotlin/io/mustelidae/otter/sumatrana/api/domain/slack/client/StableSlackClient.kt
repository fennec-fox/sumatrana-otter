package io.mustelidae.otter.sumatrana.api.domain.slack.client

import io.mustelidae.otter.sumatrana.api.config.AppEnvironment
import io.mustelidae.otter.sumatrana.api.domain.slack.BlockKit
import io.mustelidae.otter.sumatrana.api.domain.slack.SlackResources
import io.mustelidae.otter.sumatrana.utils.ClientSupport
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient
import org.slf4j.LoggerFactory

class StableSlackClient(
    private val env: AppEnvironment.EndPoint.Slack,
    private val httpClient: CloseableHttpClient
): SlackClient, ClientSupport(
    SlackResources.getMapper(),
    env.logging,
    LoggerFactory.getLogger(StableSlackClient::class.java)
) {
    override fun incomingWebhook(path: String, payload: SlackResources.Payload) {
        val header = listOf("Content-Type" to "application/json")

        val url = "${env.host}/$path"

        httpClient.post(url, header, payload).orElseThrow()
    }

    override fun chatBot(token: String, channel: String, payload: SlackResources.Payload) {
        val url = "${env.host}/api/chat.postMessage"

        val header = listOf(
            "Content-Type" to "application/json",
            "Authorization" to "Bearer $token"
        )

        httpClient.post(
            url,
            header,
            PostMessage(
                payload.blocks,
                channel
            )
        ).orElseThrow()
    }

    private data class PostMessage(
        val blocks: List<BlockKit.Block>,
        val channel: String,
    )
}