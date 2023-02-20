package io.mustelidae.otter.sumatrana.api.domain.sentry

import io.mustelidae.otter.sumatrana.api.domain.tunneling.SentryToSlackTunneling
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(
    indexes = [
        Index(name = "IDX_SENTRY_KEYCODE", columnList = "keyCode", unique = true),
    ]
)
class Sentry(
    @Column(name = "keyCode")
    val key: String,
    val projectId: Long,
    val projectName: String
) {

    @Id
    @GeneratedValue
    var id: Long? = null
        protected set

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "sentry")
    var sentryToSlackTunnelings: MutableList<SentryToSlackTunneling> = arrayListOf()

    fun addBy(sentryToSlackTunneling: SentryToSlackTunneling) {
        sentryToSlackTunnelings.add(sentryToSlackTunneling)
        if (sentryToSlackTunneling.sentry != this)
            sentryToSlackTunneling.setBySentry(this)
    }
}
