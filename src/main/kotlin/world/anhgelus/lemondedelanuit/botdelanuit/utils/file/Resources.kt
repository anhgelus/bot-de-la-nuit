package world.anhgelus.lemondedelanuit.botdelanuit.utils.file

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.stream.Collectors

object Resources {
    fun getResourceFileAsString(fileName: String): String? {
        val clazz = this.javaClass
        val input = clazz.getResourceAsStream(fileName) ?: return null
        val isr = InputStreamReader(input, StandardCharsets.UTF_8)
        val reader = BufferedReader(isr)
        return reader.lines().collect(Collectors.joining(System.lineSeparator()))
    }
}