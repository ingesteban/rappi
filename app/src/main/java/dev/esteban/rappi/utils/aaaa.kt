package dev.esteban.rappi.utils

fun getLanguageCode(code: String): String {
    val languages = mutableMapOf<String, String>()
    languages["aa"] = "Afar"
    languages["ab"] = "Abkhaz"
    languages["ae"] = "Avestan"
    languages["af"] = "Africanos"
    languages["ak"] = "Akan"
    languages["am"] = "Amárico"
    languages["an"] = "Aragonés"
    languages["ar"] = "Árabe"
    languages["as"] = "Assamese"
    languages["av"] = "Avaric"
    languages["ay"] = "Aymara"
    languages["az"] = "Azerbaiyán"

    return languages[code] ?: "No soportado"
}
