package io.mustelidae.otter.sumatrana

import io.mustelidae.otter.sumatrana.api.config.AppEnvironment
import io.mustelidae.otter.sumatrana.api.config.FlowTestSupport
import io.mustelidae.otter.sumatrana.api.domain.sentry.repository.SentryRepository
import io.mustelidae.otter.sumatrana.api.domain.slack.repository.SlackRepository
import io.mustelidae.otter.sumatrana.api.domain.tunneling.repository.SentryToSlackTunnelingRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class ApplicationInitializerTest: FlowTestSupport() {
    @Autowired
    private lateinit var sentryRepository: SentryRepository
    @Autowired
    private lateinit var slackRepository: SlackRepository
    @Autowired
    private lateinit var sentryToSlackTunnelingRepository: SentryToSlackTunnelingRepository
    @Autowired
    private lateinit var appEnvironment: AppEnvironment

    @Test
    fun run() {
        val applicationInitializer = ApplicationInitializer(
            appEnvironment,
            slackRepository,
            sentryRepository,
            sentryToSlackTunnelingRepository
        )

        applicationInitializer.run()
    }
}