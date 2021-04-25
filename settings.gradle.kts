rootProject.name = "apus"

include(":server")
include(":client")
include(":client:app")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        maven {
            name = "artifactory-menkalian"
            url = uri("http://server.menkalian.de:8081/artifactory/menkalian")
            isAllowInsecureProtocol = true
        }
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("com.android")) {
                useModule("com.android.tools.build:gradle:4.1.3")
            }
        }
    }
}