package me.leon.ext

enum class EncodeType(val type: String) {
    Base64("base64"),
    Base64Safe("urlBase64"),
    Unicode("unicode"),
    Hex("hex"),
    Binary("binary"),
    UrlEncode("urlencode"),
    Base32("base32"),
    Base16("base16"),
    Base58("base58"),
    Base58Check("base58Check"),
}
