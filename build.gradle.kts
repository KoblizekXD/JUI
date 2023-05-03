plugins {
    id("java")
}

group = "org.jui"
version = "0.1.0-BETA"

repositories {
    mavenCentral()
}

dependencies {
    // Log4J
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")

    // JNA
    implementation("net.java.dev.jna:jna:5.13.0")
    implementation("net.java.dev.jna:jna-platform:5.13.0")

    // Utilities
    implementation("com.google.guava:guava:31.1-jre")

    // JUnit
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}