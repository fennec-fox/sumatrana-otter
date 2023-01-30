package io.mustelidae.otter.sumatrana.api.domain.slack.client

import io.mustelidae.otter.sumatrana.api.config.AppEnvironment
import io.mustelidae.otter.sumatrana.utils.RestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SlackClientConfiguration(
    private val appEnvironment: AppEnvironment
) {

    @Bean
    fun slackClient(): SlackClient {
        val env = appEnvironment.endPoint.slack

        return if (env.useDummy)
            DummySlackClient()
        else {
            StableSlackClient(env, RestClient.new(env))
        }
    }
}