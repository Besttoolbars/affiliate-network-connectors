plugins {
    kotlin("jvm") version "1.3.72" apply false
    id("com.jfrog.bintray") version "1.8.5" apply false
    id("com.github.ben-manes.versions") version "0.28.0"
}

extra["retrofit.version"] = "2.9.0"
extra["jackson.version"] = "2.11.0"

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }

    group = "com.alexbogovich"
    version = "0.0.6"
}

