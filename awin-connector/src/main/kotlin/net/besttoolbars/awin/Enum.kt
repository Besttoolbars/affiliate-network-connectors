package net.besttoolbars.awin

enum class TransactionStatus {
    PENDING,
    APPROVED,
    DECLINE,
    DELETED;

    override fun toString(): String = name.toLowerCase()
}

enum class TransactionDateType {
    TRANSACTION,
    VALIDATION;

    override fun toString(): String = name.toLowerCase()
}

/**
 * @property BR Brazil programs running in BRL
 * @property BU Brazil programs running in USD
 */

enum class CountryCode {
    AT,
    AU,
    BE,
    BR,
    BU,
    CA,
    CH,
    DE,
    DK,
    ES,
    FI,
    FR,
    GB,
    IE,
    IT,
    NL,
    NO,
    PL,
    SE,
    US,
}

enum class TransactionTimeZone(val zone: String) {
    EUROPE_BERLIN("Europe/Berlin"),
    EUROPE_PARIS("Europe/Paris"),
    EUROPE_LONDON("Europe/London"),
    EUROPE_DUBLIN("Europe/Dublin"),
    CANADA_EASTERN("Canada/Eastern"),
    CANADA_CENTRAL("Canada/Central"),
    CANADA_MOUNTAIN("Canada/Mountain"),
    CANADA_PACIFIC("Canada/Pacific"),
    US_EASTERN("US/Eastern"),
    US_CENTRAL("US/Central"),
    US_MOUNTAIN("US/Mountain"),
    US_PACIFIC("US/Pacific"),
    UTC("UTC");

    override fun toString(): String = zone
}