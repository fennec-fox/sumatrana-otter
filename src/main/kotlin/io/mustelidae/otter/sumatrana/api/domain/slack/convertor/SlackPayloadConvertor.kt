package io.mustelidae.otter.sumatrana.api.domain.slack.convertor

import io.mustelidae.otter.sumatrana.api.domain.sentry.Sentry
import io.mustelidae.otter.sumatrana.api.domain.sentry.SentryResources
import io.mustelidae.otter.sumatrana.api.domain.slack.SlackResources

interface SlackPayloadConvertor {

    fun sentryToSlack(sentry: Sentry, payload: SentryResources.Payload): SlackResources.Payload
}