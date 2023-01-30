package io.mustelidae.otter.sumatrana.api.domain.sentry

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Index
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
}