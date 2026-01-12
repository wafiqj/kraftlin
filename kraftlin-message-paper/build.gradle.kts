dependencies {
    api(project(":kraftlin-message-core"))

    compileOnly(libs.paper.api)

    testImplementation(kotlin("test"))
    testImplementation(libs.junit.jupiter)
}
