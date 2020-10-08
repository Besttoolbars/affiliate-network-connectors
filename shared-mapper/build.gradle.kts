plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.jfrog.bintray")
}

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

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    publish = true
    override = true
    setPublications("mavenJava")
    pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
        repo = "repo"
        name = "shared-mapper"
        userOrg = "besttoolbars"
        websiteUrl = "https://github.com/Besttoolbars/affiliate-network-connectors/shared-mapper"
        githubRepo = "Besttoolbars/affiliate-network-connectors"
        vcsUrl = "https://github.com/Besttoolbars/affiliate-network-connectors.git"
        description = "Shared xml mapper for affiliate connectors"
        setLabels("kotlin", "jvm", "xml mapper for affiliate connectors")
        setLicenses("Apache-2.0")
    })
}
