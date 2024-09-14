plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "todo"

include("domain")
include("application")
include("infrastructure")
include("api")
include("graphql")
include("notion-client")
include("demo")
