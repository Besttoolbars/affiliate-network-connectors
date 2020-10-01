package net.besttoolbars.cj

enum class CjLanguageEnum(
    val code: Int,
    val iso6391: String,
    val internalName: String
) {
    ENGLISH(9, "en", "English"),
    SPANISH(29, "es", "Spanish"),
    FRENCH(11, "fr", "French"),
    GERMAN(12, "de", "German"),
    SWEDISH(30, "sv", "Swedish"),
    ARABIC(1, "ar", "Arabic"),
    BENGALI(2, "bn", "Bengali"),
    BULGARIAN(3, "br", "Bulgarian"),
    CHINESE_SIMPLIFIED(4, "zh", "Chinese (Simplified)"),
    CHINESE_TRADITIONAL(5, "zh-Hant", "Chinese (Traditional)"),
    CZECH(6, "cs", "Czech"),
    DANISH(7, "da", "Danish"),
    DUTCH(8, "nl", "Dutch"),
    FINNISH(10, "fj", "Finnish"),
    GREEK(13, "el", "Greek"),
    HEBREW(14, "he", "Hebrew"),
    HINDI(15, "hi", "Hindi"),
    HUNGARIAN(16, "hu", "Hungarian"),
    INDONESIAN(17, "id", "Indonesian"),
    ITALIAN(18, "it", "Italian"),
    JAPANESE(19, "ja", "Japanese"),
    KOREAN(20, "ko", "Korean"),
    MALAY(21, "ms", "Malay"),
    NORWEGIAN(22, "no", "Norwegian"),
    PERSIAN(23, "fa", "Persian"),
    POLISH(24, "pl", "Polish"),
    PORTUGUESE(25, "pt", "Portuguese"),
    ROMANIAN(26, "ro", "Romanian"),
    RUSSIAN(27, "ru", "Russian"),
    SLOVENIAN(28, "sl", "Slovenian"),
    TAMIL(31, "ta", "Tamil"),
    THAI(32, "th", "Thai"),
    TURKISH(33, "tr", "Turkish"),
    UKRAINIAN(34, "uk", "Ukrainian"),
    VIETNAMESE(35, "vi", "Vietnamese");

    override fun toString(): String = code.toString()
}

enum class CjCommissionRateType {
    PERCENT,
    FIXED,
    FIXED_PER_ORDER
}
