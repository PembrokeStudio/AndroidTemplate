package pembroke.studio.template

enum class ApiEndpoint(val url: String) {
    Production(""),
    Staging(""),
    Local("");
}