plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.jfrog.artifactory")
}

version = "1.0.0"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    api("com.fasterxml.jackson.core:jackson-databind:${property("jackson.version")}")
    api("com.fasterxml.jackson.module:jackson-module-kotlin:${property("jackson.version")}")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${property("jackson.version")}")
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:${property("jackson.version")}")
    api("com.fasterxml.woodstox:woodstox-core:6.2.1")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType(JavaCompile::class) {
    options.compilerArgs.add("-parameters")
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

publishing {
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())
        }
    }
}

artifactory {
    setContextUrl("https://softomate.jfrog.io/artifactory")
    clientConfig.setIncludeEnvVars(true)
    clientConfig.info.setBuildName("shared-mapper")
    publish(closureOf<org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig> {
        repository(delegateClosureOf<groovy.lang.GroovyObject> {
            setProperty("repoKey", "jvm-modules")
            setProperty("username", System.getenv("JFROG_MODULES_USER"))
            setProperty("password", System.getenv("JFROG_MODULES_PASS"))
        })
        defaults(delegateClosureOf<groovy.lang.GroovyObject> {
            invokeMethod("publications", "mavenJava")
            setProperty("publishPom", true)
            setProperty("publishArtifacts", true)
        })
    })
}
