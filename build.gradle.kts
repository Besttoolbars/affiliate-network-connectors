plugins {
    kotlin("jvm") version "1.3.71" apply false
}

extra["retrofit.version"] = "2.7.1"
extra["jackson.version"] = "2.10.1"

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }

    group = "com.alexbogovich"
    version = "0.0.1"
}

