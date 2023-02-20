package io.mustelidae.otter.sumatrana.api.domain.tunneling

import io.mustelidae.otter.sumatrana.api.domain.sentry.Sentry
import io.mustelidae.otter.sumatrana.api.domain.slack.Slack
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class SentryToSlackTunneling(
    val slackChannel: String,
    val style: String? = "default"
) {

    @Id
    @GeneratedValue
    var id: Long? = null
        protected set

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "sentry_id")
    var sentry: Sentry? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "slack_id")
    var slack: Slack? = null

    fun setBySentry(sentry: Sentry) {
        this.sentry = sentry
        if (sentry.sentryToSlackTunnelings.contains(this).not())
            sentry.addBy(this)
    }

    fun setBySlack(slack: Slack) {
        this.slack = slack
        if (slack.sentryToSlackTunnelings.contains(this).not())
            slack.addBy(this)
    }
}
