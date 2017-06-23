package kondol.mnist

import org.slf4j.LoggerFactory
import java.io.Closeable
import java.nio.file.Path

class ImageReader (
    private val imageFile: Path,
    private val labelFile: Path
): Closeable {
    private val logger = LoggerFactory.getLogger(ImageReader::class.java)
    
    private val imageFileReader = ImageFileReader(this.imageFile)
    private val labelFileReader = LabelFileReader(this.labelFile)
    
    fun readImage(imageIndex: Int): Image {
        val pixels = this.imageFileReader.readPixels(imageIndex)
        val label = this.labelFileReader.readLabel(imageIndex)
        return Image(label, pixels)
    }
    
    override fun close() {
        this.closeQuietly(this.imageFileReader)
        this.closeQuietly(this.labelFileReader)
    }

    private fun closeQuietly(closeable: Closeable) {
        try {
            closeable.close()
        } catch (e: Exception) {
            logger.warn(e.message)
        }
    }
}