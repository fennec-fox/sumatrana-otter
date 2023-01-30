package io.mustelidae.otter.sumatrana.api.domain.tunneling

import io.mustelidae.otter.sumatrana.api.domain.sentry.SentryResources
import io.mustelidae.otter.sumatrana.api.domain.slack.Slack
import io.mustelidae.otter.sumatrana.api.domain.slack.client.SlackClient
import io.mustelidae.otter.sumatrana.api.domain.slack.convertor.DefaultStyleSlackPayloadConvertor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SentryErrorTunnelingInteraction(
    private val tunnelingFinder: TunnelingFinder,
    private val slackClient: SlackClient
) {

    fun toSlack(key: String, request: SentryResources.Payload) {
        val sentryToSlackTunneling = tunnelingFinder.sentryToSlack(key)
        val sentry = requireNotNull(sentryToSlackTunneling.sentry)
        val slack = requireNotNull(sentryToSlackTunneling.slack)
        val channel = sentryToSlackTunneling.slackChannel

        val convertor = when(sentryToSlackTunneling.style){
            "default" -> DefaultStyleSlackPayloadConvertor
            else -> DefaultStyleSlackPayloadConvertor
        }

        val payload =  convertor.sentryToSlack(sentry, request)

        when (slack.type) {
            Slack.Type.BOT -> slackClient.chatBot(slack.value, channel, payload)
            Slack.Type.WEBHOOK -> slackClient.incomingWebhook(slack.value, payload)
        }
    }

}