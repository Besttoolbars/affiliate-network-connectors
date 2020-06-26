
For all modules? `settings.gradle.kts`
```$kotlin settings.gradle.kts
includeBuild("PATH_TO_REPO/affiliate-network-connectors")
```


For specific module
`settings.gradle.kts`
```$kotlin
includeBuild("PATH_TO_REPO/affiliate-network-connectors") {
    dependencySubstitution {
        substitute(module("net.besttoolbars:cj-connector")).with(project(":cj-connector"))
    }
}
```
