package io.mustelidae.otter.sumatrana.api.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app")
class AppEnvironment {

    var endPoint = EndPoint()
    var tunneling = Tunneling()
    open class ConnInfo {
        var host: String = "localhost"
        var timeout: Long = 2
        var logging: Boolean = false
        var useDummy: Boolean = false
        var perRoute: Int = 50
        var connTotal: Int = 500
        var connLiveDuration: Long = 60
    }

    class EndPoint {
        var slack = Slack()
        var sentry = Sentry()

        class Slack: ConnInfo()
        class Sentry: ConnInfo()
    }

    class Tunneling {
        var target = Target()
        var rule = Rule()

        class Target {
            var sentry = Sentry()
            var slack = Slack()
            class Sentry {
                var projects: List<Project> = emptyList()
                @NoArg
                data class Project(
                    var key: String,
                    var projectId: Long,
                    var projectName: String
                )
            }

            class Slack {
                var senders: List<Sender> = emptyList()
                @NoArg
                data class Sender(
                    var key: String,
                    var type: String,
                    var value: String
                )
            }
        }
        class Rule {
            var sentryToSlackMappings: List<SentryToSlackMapping> = emptyList()
            @NoArg
            data class SentryToSlackMapping(
                var key: String,
                var slackChannel: String,
                var sentryKey: String,
                var slackKey: String,
                var style: String? = null,
            )
        }
    }
}
