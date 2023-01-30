package io.mustelidae.otter.sumatrana.api.permission

class AdminPermission(
    private val id: Long
) : Permission {
    override fun isValid(): Boolean = true
}
