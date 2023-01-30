package io.mustelidae.otter.sumatrana.api.domain.slack.convertor

import io.mustelidae.otter.sumatrana.api.domain.sentry.Sentry
import io.mustelidae.otter.sumatrana.api.domain.sentry.SentryResources
import io.mustelidae.otter.sumatrana.api.domain.slack.BlockKit
import io.mustelidae.otter.sumatrana.api.domain.slack.SlackResources

object DefaultStyleSlackPayloadConvertor: SlackPayloadConvertor {

    /**
     * Slack message style
     * --------------------------
     * \[alpha] POST /v1/sample
     * IIIlegalArgumentException
     * Project(3): otter | 2023-01-25 07:32:12
     * ! No enum constant io.mustelidate
     * --------------------------
     */
    override fun sentryToSlack(sentry: Sentry, payload: SentryResources.Payload): SlackResources.Payload {
        val builder = StringBuilder()
        val data = requireNotNull(payload.data.event?: payload.data.error)
        val firstExceptionLine = data.exception?.values?.first()
        // [alpha] POST /v1/sample
        run {
            data.environment?.let {
                builder.append("*[$it]* ")
            }

            val errorPath = data.culprit?: data.request?.url?: firstExceptionLine?.type?: "Click Me"
            val sentryUrl = data.issueUrl?: data.webUrl

            if(sentryUrl.isNullOrEmpty().not())
                builder.append("<$sentryUrl|$errorPath>\n")
        }


        // IIIlegalArgumentException
        run {
            if(firstExceptionLine != null) {
                firstExceptionLine.type?.let {
                    builder.append(it)
                }
            }
        }

        val sectionText = builder.toString()

        return SlackResources.Payload(
            listOf(
                BlockKit.Section(
                    BlockKit.Text(
                        BlockKit.Text.Type.mrkdwn,
                        sectionText
                    )
                ),
                BlockKit.Context(
                    listOf(
                        BlockKit.Text(
                            BlockKit.Text.Type.plain_text,
                            "Project(${sentry.projectId}): ${sentry.projectName} | ${data.datetime}"
                        ),
                        BlockKit.Text(
                            BlockKit.Text.Type.plain_text,
                            ":exclamation: ${firstExceptionLine?.value}"
                        )
                    )
                )
            )
        )
    }


}