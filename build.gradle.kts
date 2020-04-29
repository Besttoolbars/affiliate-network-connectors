plugins {
    kotlin("jvm") version "1.3.71" apply false
    id("com.jfrog.bintray") version "1.8.5" apply false
    id("com.github.ben-manes.versions") version "0.28.0"
}

extra["retrofit.version"] = "2.8.1"
extra["jackson.version"] = "2.10.3"

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }

    group = "com.alexbogovich"
    version = "0.0.3"
}

