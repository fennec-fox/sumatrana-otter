package io.mustelidae.otter.sumatrana.api.domain.slack.client

import io.mustelidae.otter.sumatrana.api.config.AppEnvironment
import io.mustelidae.otter.sumatrana.api.domain.slack.BlockKit
import io.mustelidae.otter.sumatrana.api.domain.slack.SlackResources
import io.mustelidae.otter.sumatrana.utils.RestClient
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class StableSlackClientTest {
    /**
     * ex) https://xxxx.slack.com
     */
    private val slackHost = ""

    /**
     * ex) alarm-test
     */
    private val channel = ""

    /**
     * ex) Bot User OAuth Token
     */
    private val token = ""

    @Test
    @Disabled
    fun chatBot() {
        val env = AppEnvironment.EndPoint.Slack().apply {
            host = slackHost
        }

        val client = StableSlackClient(env, RestClient.new(env))
        val payload = SlackResources.Payload(
            listOf(
                BlockKit.Section(
                    BlockKit.Text(
                        BlockKit.Text.Type.plain_text,
                        "sample test"
                    )
                )
            )
        )

        client.chatBot(token, channel, payload)
    }
}