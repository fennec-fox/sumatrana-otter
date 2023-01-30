package io.mustelidae.otter.sumatrana.api.domain.slack.client

import io.mustelidae.otter.sumatrana.api.domain.slack.SlackResources

interface SlackClient {

    fun incomingWebhook(path: String, payload: SlackResources.Payload)

    fun chatBot(token: String, channel:String, payload: SlackResources.Payload)
}