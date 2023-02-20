package io.mustelidae.otter.sumatrana.api.domain.api

import io.mustelidae.otter.sumatrana.api.common.Reply
import io.mustelidae.otter.sumatrana.api.common.toReply
import io.mustelidae.otter.sumatrana.api.domain.sentry.SentryResources
import io.mustelidae.otter.sumatrana.api.domain.tunneling.SentryErrorTunnelingInteraction
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/webhook")
class SentryForwardController(
    private val sentryErrorTunnelingInteraction: SentryErrorTunnelingInteraction
) {

    /**
     * Sentry to Slack
     * @ref https://docs.sentry.io/product/integrations/integration-platform/webhooks/
     */
    @PostMapping("/from/sentry/to/slack")
    fun sentryToSlack(
        @RequestHeader("sentry-hook-resource") type: String,
        @RequestBody request: SentryResources.Payload
    ): Reply<Unit> {

        when (type) {
            "error", "event_alert", "event" -> sentryErrorTunnelingInteraction.toSlack(request)
        }
        return Unit.toReply()
    }
}
