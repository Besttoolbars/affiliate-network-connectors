import org.jetbrains.kotlin.gradle.tasks.KotlinCompile;

plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.jfrog.artifactory")
}

version = "0.0.1"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":shared-mapper"))

    api("com.squareup.retrofit2:retrofit:${property("retrofit.version")}")
    implementation("com.squareup.retrofit2:converter-jackson:${property("retrofit.version")}")

    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.2.0")
    testImplementation("commons-io:commons-io:2.6")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<JavaCompile> {
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
    clientConfig.isIncludeEnvVars = true
    clientConfig.info.buildName = "pdl-profit-connector"
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