plugins {
    kotlin("jvm")
    `maven-publish`
    id("com.jfrog.bintray")
}

version = "1.0.0"

dependencies {
    implementation(project(":shared-mapper"))
    implementation(kotlin("stdlib-jdk8"))
    api("com.squareup.retrofit2:retrofit:${property("retrofit.version")}")
    api("com.google.guava:guava:29.0-jre")
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
    setPublications("mavenJava")
    pkg(delegateClosureOf<com.jfrog.bintray.gradle.BintrayExtension.PackageConfig> {
        repo = "repo"
        name = "shared-retrofit-extensions"
        userOrg = "besttoolbars"
        websiteUrl = "https://github.com/Besttoolbars/affiliate-network-connectors/shared-retrofit-extensions"
        githubRepo = "Besttoolbars/affiliate-network-connectors"
        vcsUrl = "https://github.com/Besttoolbars/affiliate-network-connectors.git"
        description = "Shared retrofit extensions for affiliate connectors"
        setLabels("kotlin", "jvm", "xml mapper for affiliate connectors")
        setLicenses("Apache-2.0")
    })
}
