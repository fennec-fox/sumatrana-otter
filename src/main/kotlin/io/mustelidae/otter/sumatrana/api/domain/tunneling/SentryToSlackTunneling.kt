package io.mustelidae.otter.sumatrana.api.domain.tunneling

import io.mustelidae.otter.sumatrana.api.domain.sentry.Sentry
import io.mustelidae.otter.sumatrana.api.domain.slack.Slack
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(
    indexes = [
        Index(name = "SENTRY_To_SLACK_TUNNELING_KEYCODE", columnList = "keyCode", unique = true),
    ]
)
class SentryToSlackTunneling(
    @Column(name = "keyCode")
    val key: String,
    val slackChannel: String,
    val style: String? = "default"
) {

    @Id
    @GeneratedValue
    var id: Long? = null
        protected set

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "sentry_id")
    var sentry:Sentry? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "slack_id")
    var slack: Slack? = null

    fun setBy(sentry: Sentry) {
        this.sentry = sentry
    }

    fun setBy(slack: Slack) {
        this.slack = slack
    }
}