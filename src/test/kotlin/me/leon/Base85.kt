package me.leon

import me.leon.base.baseNDecode
import me.leon.base.baseNEncode

const val BASE85_MAP =
    "!\"#\$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~"

fun String.base85() =
    toByteArray()
        .toList()
        .chunked(4)
        .map {
            if (it.size != 4)
                ArrayList(it).apply {
                    for (i in 0..(4 - 1 - it.size)) add(0.toByte()).also { println("add $i") }
                }
            else it
        }
        .fold(StringBuilder()) { acc, list ->
            acc.apply { acc.append(list.toByteArray().baseNEncode(85, BASE85_MAP)) }
        }

fun String.base85Decode() =
    String(
        toList()
            .chunked(5)
            .map { it.joinToString("").baseNDecode(85, BASE85_MAP) }
            .fold(mutableListOf<Byte>()) { acc, bytes -> acc.apply { acc.addAll(bytes.toList()) } }
            .filterNot { it in 0..31 || it in 128..255 }
            .toByteArray()
    )
