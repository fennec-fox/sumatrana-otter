package io.mustelidae.otter.sumatrana.api.domain.tunneling.repository

import io.mustelidae.otter.sumatrana.api.domain.tunneling.SentryToSlackTunneling
import org.springframework.data.jpa.repository.JpaRepository

interface SentryToSlackTunnelingRepository: JpaRepository<SentryToSlackTunneling, Long> {

    fun findByKey(key:String): SentryToSlackTunneling?
}