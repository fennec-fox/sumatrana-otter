package io.mustelidae.otter.sumatrana.api.domain.slack.client

import io.mustelidae.otter.sumatrana.api.domain.slack.SlackResources
import io.mustelidae.otter.sumatrana.utils.toJson
import org.slf4j.LoggerFactory

class DummySlackClient: SlackClient {

    private val log = LoggerFactory.getLogger(this::class.java)
    override fun incomingWebhook(path: String, payload: SlackResources.Payload) {
        log.info("send webhook payload is ${payload.toJson()}")
    }

    override fun chatBot(token: String, channel: String, payload: SlackResources.Payload) {
        log.info("send chat bot is ${payload.blocks}")
    }
}