package io.mustelidae.otter.sumatrana.api.config

import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun default(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("API")
        .addOpenApiCustomiser {
            it.info.version("v1")
        }
        .packagesToScan("io.mustelidae.otter.sumatrana.api.domain")
        .pathsToMatch("/v1/**")
        .pathsToExclude(
            "/v1/maintenance/**",
            "/v1/migration/**",
            "/v1/bridge/**",
            "/v1/batch/**",
            "/v1/gateway/**",
            "/v1/open-api/**",
            "/v1/internal-development/**"
        )
        .build()

    @Bean
    fun maintenance(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("Maintenance")
        .addOpenApiCustomiser {
            it.info.version("v1")
        }
        .packagesToScan("io.mustelidae.otter.sumatrana.api.domain")
        .pathsToMatch("/v1/maintenance/**")
        .build()

    @Bean
    fun migration(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("Migration")
        .addOpenApiCustomiser {
            it.info.version("v1")
        }
        .packagesToScan("io.mustelidae.otter.sumatrana.api.domain")
        .pathsToMatch("/v1/migration/**")
        .build()

    @Bean
    fun bridge(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("Bridge")
        .addOpenApiCustomiser {
            it.info.version("v1")
        }
        .packagesToScan("io.mustelidae.otter.sumatrana.api.domain")
        .pathsToMatch("/v1/bridge/**")
        .build()

    @Bean
    fun batch(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("Batch")
        .addOpenApiCustomiser {
            it.info.version("v1")
        }
        .packagesToScan("io.mustelidae.otter.sumatrana.api.domain")
        .pathsToMatch("/v1/batch/**")
        .build()

    @Bean
    fun gateway(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("Gateway")
        .addOpenApiCustomiser {
            it.info.version("v1")
        }
        .packagesToScan("io.mustelidae.otter.sumatrana.api.domain")
        .pathsToMatch("/v1/gateway/**")
        .build()

    @Bean
    fun openApi(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("Open-API")
        .addOpenApiCustomiser {
            it.info.version("v1")
        }
        .packagesToScan("io.mustelidae.otter.sumatrana.api.domain")
        .pathsToMatch("/v1/open-api/**")
        .build()

    @Bean
    fun internalDevelopment(): GroupedOpenApi = GroupedOpenApi.builder()
        .group("Internal-Development")
        .addOpenApiCustomiser {
            it.info.version("v1")
        }
        .packagesToScan("io.mustelidae.otter.sumatrana.api.domain")
        .pathsToMatch("/v1/internal-development/**")
        .build()
}
