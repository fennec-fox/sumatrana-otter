package io.mustelidae.otter.sumatrana.api.domain.sentry

import io.mustelidae.otter.sumatrana.api.config.DataNotFindException
import io.mustelidae.otter.sumatrana.api.domain.sentry.repository.SentryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class SentryFinder(
    private val sentryRepository: SentryRepository
) {

    fun findOneByProjectId(id: Long): Sentry {
        return sentryRepository.findByProjectId(id) ?: throw DataNotFindException(id, "sentry project id not found")
    }
}
