package io.mustelidae.otter.sumatrana.api.domain.api

import io.mustelidae.otter.sumatrana.api.domain.sentry.SentryResources
import io.mustelidae.otter.sumatrana.utils.toJson
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

class SentryForwardControllerFlow(
    private val mockMvc: MockMvc
) {
    fun sentryToSlack(
        type: String,
        key: String,
        request: SentryResources.Payload
    ) {
        val uri = linkTo<SentryForwardController> { sentryToSlack(type, key, request) }.toUri()

        mockMvc.post(uri) {
            header("sentry-hook-resource", type)
            contentType = MediaType.APPLICATION_JSON
            content = request.toJson()
        }.andExpect {
            status { is2xxSuccessful() }
        }.andReturn()
            .response
    }
}