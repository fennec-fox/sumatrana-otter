package io.mustelidae.otter.sumatrana.api.domain.slack

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.Table


@Entity
@Table(
    indexes = [
        Index(name = "IDX_SLACK_KEYCODE", columnList = "keyCode", unique = true),
    ]
)
class Slack(
    @Column(name = "keyCode")
    val key: String,
    val type: Type,
    /**
     * If the type is bot, there is token information.
     * If the type is webhook, there is a url path.
     */
    @Column(name = "variable")
    val value: String
) {
    @Id
    @GeneratedValue
    var id: Long? = null
        protected set

    enum class Type {
        BOT,
        WEBHOOK
    }
}