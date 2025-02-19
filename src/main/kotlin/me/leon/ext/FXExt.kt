package me.leon.ext

import java.awt.Desktop
import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javafx.embed.swing.SwingFXUtils
import javafx.scene.image.Image
import javafx.scene.input.Clipboard
import javafx.scene.input.ClipboardContent
import javafx.stage.FileChooser
import javafx.stage.Window
import javax.imageio.ImageIO

fun String.copy() =
    Clipboard.getSystemClipboard().setContent(ClipboardContent().apply { putString(this@copy) })

fun clipboardText(): String? = Clipboard.getSystemClipboard().string

fun clipboardImage(): Image? = Clipboard.getSystemClipboard().image

fun Image.copy() =
    Clipboard.getSystemClipboard().setContent(ClipboardContent().apply { putImage(this@copy) })

fun String.openInBrowser() = Desktop.getDesktop().browse(URL(this).toURI())

fun Image.toBufferImage(): BufferedImage = SwingFXUtils.fromFXImage(this, null)

fun BufferedImage.toFxImg(): Image = SwingFXUtils.toFXImage(this, null)

fun BufferedImage.writeFile(path: String, format: String = "png") {
    ImageIO.write(this, format, File(path))
}

fun Window.fileChooser(hint: String = "请选择文件"): File? =
    FileChooser().apply { title = hint }.showOpenDialog(this)
