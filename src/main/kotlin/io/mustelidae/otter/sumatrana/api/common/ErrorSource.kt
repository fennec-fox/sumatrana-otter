package io.mustelidae.otter.sumatrana.api.common

interface ErrorSource {
    val code: String
    val message: String
    var causeBy: Map<String, Any?>?
    var refCode: String?
}
