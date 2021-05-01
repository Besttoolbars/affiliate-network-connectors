plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.jfrog.artifactory")
}

version = "1.0.1"

dependencies {
    implementation(project(":shared-mapper"))
    implementation(kotlin("stdlib-jdk8"))
    api("com.squareup.retrofit2:retrofit:${property("retrofit.version")}")
    api("com.google.guava:guava:29.0-jre")

    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
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

tasks.withType<Test> {
    useJUnitPlatform()
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
    clientConfig.info.setBuildName("shared-retrofit-extensions")
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