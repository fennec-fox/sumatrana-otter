package io.mustelidae.otter.sumatrana.api.domain.tunneling

import io.mustelidae.otter.sumatrana.api.config.DevelopMistakeException
import io.mustelidae.otter.sumatrana.api.domain.sentry.SentryFinder
import io.mustelidae.otter.sumatrana.api.domain.sentry.SentryResources
import io.mustelidae.otter.sumatrana.api.domain.slack.Slack
import io.mustelidae.otter.sumatrana.api.domain.slack.client.SlackClient
import io.mustelidae.otter.sumatrana.api.domain.slack.convertor.DefaultStyleSlackPayloadConvertor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SentryErrorTunnelingInteraction(
    private val sentryFinder: SentryFinder,
    private val slackClient: SlackClient
) {

    fun toSlack(request: SentryResources.Payload) {
        val projectId = request.data.error?.project ?: request.data.event?.project ?: throw DevelopMistakeException("sentry payload not found project id")
        val sentry = sentryFinder.findOneByProjectId(projectId.toLong())

        for (sentryToSlackTunneling in sentry.sentryToSlackTunnelings) {
            this.toSlack(sentryToSlackTunneling, request)
        }
    }

    fun toSlack(sentryToSlackTunneling: SentryToSlackTunneling, request: SentryResources.Payload) {
        val sentry = requireNotNull(sentryToSlackTunneling.sentry)
        val slack = requireNotNull(sentryToSlackTunneling.slack)

        val convertor = when (sentryToSlackTunneling.style) {
            "default" -> DefaultStyleSlackPayloadConvertor
            else -> DefaultStyleSlackPayloadConvertor
        }

        val payload = convertor.sentryToSlack(sentry, request)

        when (slack.type) {
            Slack.Type.BOT -> slackClient.chatBot(slack.value, sentryToSlackTunneling.slackChannel, payload)
            Slack.Type.WEBHOOK -> slackClient.incomingWebhook(slack.value, payload)
        }
    }
}
