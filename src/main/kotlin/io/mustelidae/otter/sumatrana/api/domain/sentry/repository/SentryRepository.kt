package io.mustelidae.otter.sumatrana.api.domain.sentry.repository

import io.mustelidae.otter.sumatrana.api.domain.sentry.Sentry
import org.springframework.data.jpa.repository.JpaRepository

interface SentryRepository: JpaRepository<Sentry, Long>