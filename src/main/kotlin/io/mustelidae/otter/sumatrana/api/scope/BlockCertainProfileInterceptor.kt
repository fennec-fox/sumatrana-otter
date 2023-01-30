package io.mustelidae.otter.sumatrana.api.scope

import io.mustelidae.otter.sumatrana.api.config.AccessDeniedException
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BlockCertainProfileInterceptor(
    val env: Environment
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if ((handler is HandlerMethod).not())
            return true

        val handlerMethod = handler as HandlerMethod
        val hasClass = handlerMethod.method.declaringClass.isAnnotationPresent(BlockCertainProfile::class.java)

        val blockCertainProfile = if (hasClass) {
            handlerMethod.method.declaringClass.getDeclaredAnnotation(BlockCertainProfile::class.java)
        } else {
            handlerMethod.getMethodAnnotation(BlockCertainProfile::class.java)
        }

        if (blockCertainProfile == null)
            return true

        val profile = env.activeProfiles.first()

        if (blockCertainProfile.profiles.contains(profile))
            throw AccessDeniedException()
        return true
    }
}
