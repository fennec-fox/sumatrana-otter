package io.mustelidae.otter.sumatrana


import io.mustelidae.otter.sumatrana.api.config.AppEnvironment
import io.mustelidae.otter.sumatrana.api.config.DevelopMistakeException
import io.mustelidae.otter.sumatrana.api.domain.sentry.Sentry
import io.mustelidae.otter.sumatrana.api.domain.sentry.repository.SentryRepository
import io.mustelidae.otter.sumatrana.api.domain.slack.Slack
import io.mustelidae.otter.sumatrana.api.domain.slack.repository.SlackRepository
import io.mustelidae.otter.sumatrana.api.domain.tunneling.SentryToSlackTunneling
import io.mustelidae.otter.sumatrana.api.domain.tunneling.repository.SentryToSlackTunnelingRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.Transactional

@Configuration
@Transactional
class ApplicationInitializer(
    private val appEnvironment: AppEnvironment,
    private val slackRepository: SlackRepository,
    private val sentryRepository: SentryRepository,
    private val sentryToSlackTunnelingRepository: SentryToSlackTunnelingRepository,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val sentryToSlackInitializer = SentryToSlackInitializer(
            slackRepository,
            sentryRepository,
            sentryToSlackTunnelingRepository
        )

        sentryToSlackInitializer.init(appEnvironment.tunneling)
    }
}

private class SentryToSlackInitializer(
    private val slackRepository: SlackRepository,
    private val sentryRepository: SentryRepository,
    private val sentryToSlackTunnelingRepository: SentryToSlackTunnelingRepository,
) {

    fun init(tunnelingSet: AppEnvironment.Tunneling) {

        val groupOfSentry = tunnelingSet.target.sentry.projects.map {
            val sentry = Sentry(it.key, it.projectId, it.projectName)
            sentryRepository.save(sentry)
            sentry
        }

        val groupOfSlack = tunnelingSet.target.slack.senders.map {
            val slack = Slack(it.key, Slack.Type.valueOf(it.type.uppercase()), it.value)
            slackRepository.save(slack)
            slack
        }

        tunnelingSet.rule.sentryToSlackMappings.forEach {

            val sentry = groupOfSentry.find { sentry: Sentry -> sentry.key == it.sentryKey }?: throw DevelopMistakeException("not found sentry key")
            val slack = groupOfSlack.find { slack: Slack -> slack.key == it.slackKey }?: throw DevelopMistakeException("not found slack key")

            val tunneling = SentryToSlackTunneling(
                it.key,
                it.slackChannel,
                it.style?: "default",
            ).apply {
                setBy(sentry)
                setBy(slack)
            }
            sentryToSlackTunnelingRepository.save(tunneling)
        }
    }
}


