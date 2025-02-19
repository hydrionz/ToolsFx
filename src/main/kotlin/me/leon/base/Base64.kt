package me.leon.base

import me.leon.ext.toBinaryString

const val BASE64_MAP = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
const val BASE64_BLOCK_SIZE = 6
const val BASE64_PADDING_SIZE = 4
const val BYTE_MASK = 0xFF

fun String.base64() =
    toByteArray()
        .toBinaryString()
        .chunked(BASE64_BLOCK_SIZE)
        //        .also { println(it.joinToString("")) }
        .joinToString("") { BASE64_MAP[it.padding("0", BASE64_BLOCK_SIZE).toInt(2)].toString() }
        .padding("=", BASE64_PADDING_SIZE) // lcm (6, 8) /6 = 4

fun ByteArray.base64() =
    toBinaryString()
        .chunked(BASE64_BLOCK_SIZE)
        //        .also { println(it.joinToString("")) }
        .joinToString("") { BASE64_MAP[it.padding("0", BASE64_BLOCK_SIZE).toInt(2)].toString() }
        .padding("=", BASE64_PADDING_SIZE) // lcm (6, 8) /6 = 4

/** 标准的Base64并不适合直接放在URL里传输，因为URL编码器会把标准Base64中的“/”和“+”字符变为形如“%XX”的形式， */
fun String.safeBase64() =
    toByteArray()
        .toBinaryString()
        .chunked(BASE64_BLOCK_SIZE)
        //        .also { println(it.joinToString("")) }
        .joinToString("") { BASE64_MAP[it.padding("0", BASE64_BLOCK_SIZE).toInt(2)].toString() }
        .padding("=", BASE64_PADDING_SIZE)
        .replace("/", "_")
        .replace("+", "-")

fun String.base64DecodeString() =
    String(
        toCharArray()
            .filter { it != '=' }
            .joinToString("") {
                BASE64_MAP.indexOf(it).toString(2).padding("0", BASE64_BLOCK_SIZE, false)
            }
            .chunked(BYTE_BITS)
            .filter { it.length == BYTE_BITS }
            //            .also { println(it.joinToString("")) }
            .map { it.toInt(2).toByte() }
            .toByteArray()
    )

fun String.base64Decode() =

    //    Base64.getDecoder().decode(this)
    toCharArray()
        .filter { it != '=' }
        .joinToString("") {
            BASE64_MAP.indexOf(it).toString(2).padding("0", BASE64_BLOCK_SIZE, false)
        }
        .chunked(BYTE_BITS)
        .filter { it.length == BYTE_BITS }
        .map { (it.toInt(2) and BYTE_MASK).toByte() }
        .toByteArray()

fun String.safeBase64Decode2() =
    String(
        this.replace("_", "/")
            .replace("-", "+")
            .toCharArray()
            .filter { it != '=' }
            .joinToString("") {
                BASE64_MAP.indexOf(it).toString(2).padding("0", BASE64_BLOCK_SIZE, false)
            }
            .chunked(BYTE_BITS)
            .filter { it.length == BYTE_BITS }
            .map { it.toInt(2).toByte() }
            .toByteArray()
    )

fun String.padding(char: String, block: Int, isAfter: Boolean = true) =
    chunked(block).joinToString("") {
        it.takeIf { it.length == block }
            ?: if (isAfter) it + char.repeat(block - it.length)
            else char.repeat(block - it.length) + it
    }
