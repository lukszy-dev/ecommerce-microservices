package com.proggramik.gateway.config

import org.springdoc.core.GroupedOpenApi
import org.springdoc.core.SwaggerUiConfigParameters
import org.springframework.cloud.gateway.route.RouteDefinitionLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun apis(
        swaggerUiConfigParameters: SwaggerUiConfigParameters,
        locator: RouteDefinitionLocator
    ): List<GroupedOpenApi> {
        val groups = ArrayList<GroupedOpenApi>()

        val definitions = locator.routeDefinitions
        definitions.filter { routeDefinition ->
            routeDefinition.id.matches(Regex(".*-service"))
                    && !routeDefinition.id.startsWith("ReactiveCompositeDiscoveryClient_")
        }.subscribe { routeDefinition ->
            val name = routeDefinition.id.replace("-service", "")
            swaggerUiConfigParameters.addGroup(name)
            groups.add(GroupedOpenApi.builder().pathsToMatch("/$name/**").group(name).build())
        }

        return groups
    }
}
