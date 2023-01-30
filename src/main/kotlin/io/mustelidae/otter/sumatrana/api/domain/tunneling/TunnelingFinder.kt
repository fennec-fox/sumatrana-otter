package io.mustelidae.otter.sumatrana.api.domain.tunneling

import io.mustelidae.otter.sumatrana.api.config.DataNotFindException
import io.mustelidae.otter.sumatrana.api.domain.tunneling.repository.SentryToSlackTunnelingRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TunnelingFinder(
    private val sentryToSlackTunnelingRepository: SentryToSlackTunnelingRepository
) {

    fun sentryToSlack(key: String): SentryToSlackTunneling {
        return sentryToSlackTunnelingRepository.findByKey(key)?: throw DataNotFindException(key, "not found key of sentry to slack")
    }
}