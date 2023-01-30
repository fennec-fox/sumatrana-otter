package io.mustelidae.otter.sumatrana.api.domain.sentry
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Sentry Alert Payload
 * @ref https://docs.sentry.io/product/integrations/integration-platform/webhooks/issue-alerts/
 */
class SentryResources {

    data class Payload (
        @JsonProperty("action") val action : String,
        @JsonProperty("data") val data : Data,
        @JsonProperty("installation") val installation : Installation,
        @JsonProperty("actor") val actor : Actor? = null,
    ) {
        data class Actor(
            @JsonProperty("id") val id: String? = null,
            @JsonProperty("name") val name: String? = null,
            @JsonProperty("type") val type: String? = null
        )

        data class Data(
            @JsonProperty("event") val event: Event? = null,
            @JsonProperty("error") val error: Event? = null,
            @JsonProperty("triggered_rule") val triggeredRule: String? = null,
            @JsonProperty("issue_alert") val issueAlert: IssueAlert? = null
        ) {
            data class Event(
                @JsonProperty("_ref") val ref: Int? = null,
                @JsonProperty("_ref_version") val refVersion: Int? = null,
                val contexts: Contexts? = null,
                val culprit: String? = null,
                val environment: String? = null,
                val datetime: String? = null,
                val dist: String? = null,
                @JsonProperty("event_id") val eventId: String? = null,
                val exception: Exception? = null,
                val fingerprint: ArrayList<String>? = arrayListOf(),
                @JsonProperty("grouping_config") val groupingConfig: GroupingConfig? = null,
                val hashes: ArrayList<String>? = arrayListOf(),
                @JsonProperty("issue_url") val issueUrl: String? = null,
                @JsonProperty("issue_id") val issueId: String? = null,
                @JsonProperty("key_id") val keyId: String? = null,
                val level: String? = null,
                @JsonProperty("location") val location: String? = null,
                @JsonProperty("logger") val logger: String? = null,
                @JsonProperty("message") val message: String? = null,
                @JsonProperty("metadata") val metadata: Metadata? = null,
                @JsonProperty("platform") val platform: String? = null,
                @JsonProperty("project") val project: Int? = null,
                @JsonProperty("received") val received: Double? = null,
                @JsonProperty("release") val release: String? = null,
                @JsonProperty("request") val request: RequestBody? = null,
                @JsonProperty("sdk") val sdk: Sdk? = Sdk(),
                @JsonProperty("tags") val tags: ArrayList<ArrayList<String>> = arrayListOf(),
                @JsonProperty("time_spent") val timeSpent: String? = null,
                @JsonProperty("timestamp") val timestamp: Double? = null,
                @JsonProperty("title") val title: String? = null,
                @JsonProperty("type") val type: String? = null,
                @JsonProperty("url") val url: String? = null,
                @JsonProperty("user") val user: User? = User(),
                @JsonProperty("version") val version: String? = null,
                @JsonProperty("web_url") val webUrl: String? = null
            ) {
                data class Contexts(
                    @JsonProperty("browser") val browser: Browser? = null,
                    @JsonProperty("os") val os: Os? = Os()
                ) {
                    data class Browser(
                        @JsonProperty("name") val name: String? = null,
                        @JsonProperty("type") val type: String? = null,
                        @JsonProperty("version") val version: String? = null
                    )

                    data class Os(
                        @JsonProperty("name") val name: String? = null,
                        @JsonProperty("type") val type: String? = null,
                        @JsonProperty("version") val version: String? = null
                    )
                }

                data class GroupingConfig(
                    @JsonProperty("enhancements") val enhancements: String? = null,
                    @JsonProperty("id") val id: String? = null
                )

                data class Exception (
                    @JsonProperty("values" ) val values : ArrayList<Values>? = arrayListOf()
                ) {
                    data class Values(
                        @JsonProperty("mechanism") val mechanism: Mechanism? = null,
                        @JsonProperty("stacktrace") val stacktrace: Stacktrace? = Stacktrace(),
                        @JsonProperty("type") val type: String? = null,
                        @JsonProperty("value") val value: String? = null
                    ) {
                        data class Mechanism(
                            @JsonProperty("data") val data: MechanismData? = null,
                            @JsonProperty("description") val description: String? = null,
                            @JsonProperty("handled") val handled: Boolean? = null,
                            @JsonProperty("help_link") val helpLink: String? = null,
                            @JsonProperty("meta") val meta: String? = null,
                            @JsonProperty("synthetic") val synthetic: String? = null,
                            @JsonProperty("type") val type: String? = null
                        ) {
                            data class MechanismData(
                                val message: String? = null,
                                val mode: String? = null,
                                val name: String? = null
                            )
                        }

                        data class Stacktrace (
                            @JsonProperty("frames" ) val frames : ArrayList<Frames>? = arrayListOf()
                        ) {
                            data class Frames(
                                @JsonProperty("abs_path") val absPath: String? = null,
                                @JsonProperty("colno") val colno: Int? = null,
                                @JsonProperty("context_line") val contextLine: String? = null,
                                @JsonProperty("data") val data: FramesData? = null,
                                @JsonProperty("errors") val errors: String? = null,
                                @JsonProperty("filename") val filename: String? = null,
                                @JsonProperty("function") val function: String? = null,
                                @JsonProperty("image_addr") val imageAddr: String? = null,
                                @JsonProperty("in_app") val inApp: Boolean? = null,
                                @JsonProperty("instruction_addr") val instructionAddr: String? = null,
                                @JsonProperty("lineno") val lineno: Int? = null,
                                @JsonProperty("module") val module: String? = null,
                                @JsonProperty("package") val framesPackage: String? = null,
                                @JsonProperty("platform") val platform: String? = null,
                                @JsonProperty("post_context") val postContext: String? = null,
                                @JsonProperty("pre_context") val preContext: String? = null,
                                @JsonProperty("raw_function") val rawFunction: String? = null,
                                @JsonProperty("symbol") val symbol: String? = null,
                                @JsonProperty("symbol_addr") val symbolAddr: String? = null,
                                @JsonProperty("trust") val trust: String? = null,
                                @JsonProperty("vals") val values: String? = null
                            ) {
                                data class FramesData(
                                    @JsonProperty("orig_in_app")val origInApp: String? = null
                                )
                            }
                        }
                    }
                }

                data class Metadata(
                    @JsonProperty("filename") val filename: String? = null,
                    @JsonProperty("type") val type: String? = null,
                    @JsonProperty("value") val value: String? = null
                )

                data class RequestBody(
                    @JsonProperty("cookies") val cookies: String? = null,
                    @JsonProperty("data") val data: String? = null,
                    @JsonProperty("env") val env: String? = null,
                    @JsonProperty("fragment") val fragment: String? = null,
                    @JsonProperty("headers") val headers: ArrayList<ArrayList<String>>? = arrayListOf(),
                    @JsonProperty("inferred_content_type") val inferredContentType: String? = null,
                    @JsonProperty("method") val method: String? = null,
                    @JsonProperty("query_string") val queryString: ArrayList<String>? = arrayListOf(),
                    @JsonProperty("url") val url: String? = null
                )

                data class Sdk(
                    @JsonProperty("integrations") val integrations: ArrayList<String>? = arrayListOf(),
                    @JsonProperty("name") val name: String? = null,
                    @JsonProperty("packages") val packages: ArrayList<Packages>? = arrayListOf(),
                    @JsonProperty("version") val version: String? = null
                ) {
                    data class Packages(
                        @JsonProperty("name") val name: String? = null,
                        @JsonProperty("version") val version: String? = null
                    )
                }

                data class User (
                    @JsonProperty("ip_address" ) val ipAddress : String? = null
                )
            }

            data class IssueAlert(
                @JsonProperty("title") val title: String? = null,
                @JsonProperty("settings") val settings: ArrayList<Settings>? = arrayListOf()
            ) {
                data class Settings(
                    @JsonProperty("name") val name: String? = null,
                    @JsonProperty("value") val value: String? = null
                )
            }
        }

        data class Installation (
            @JsonProperty("uuid" ) val uuid : String? = null
        )

    }
}



































