package dev.hungndl.todo.configuration

import dev.hungndl.todo.dataloader.TaskTypeDataLoader
import graphql.scalars.ExtendedScalars
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer
import org.springframework.graphql.execution.DataLoaderRegistrar
import graphql.schema.idl.RuntimeWiring

@Configuration
class GraphQLConfig(private val taskTypeDataLoader: TaskTypeDataLoader) {

    @Bean
    fun runtimeWiringConfigurer(): RuntimeWiringConfigurer {
        return RuntimeWiringConfigurer { builder: RuntimeWiring.Builder ->
            builder.scalar(ExtendedScalars.Date)
        }
    }

    @Bean
    fun dataLoaderRegistrar(): DataLoaderRegistrar {
        return DataLoaderRegistrar { registry, _ ->
            registry.register("taskTypeDataLoader", taskTypeDataLoader.createDataLoader())
        }
    }
}