package io.mustelidae.otter.sumatrana.api.domain.slack.repository

import io.mustelidae.otter.sumatrana.api.domain.slack.Slack
import org.springframework.data.jpa.repository.JpaRepository

interface SlackRepository: JpaRepository<Slack, Long>