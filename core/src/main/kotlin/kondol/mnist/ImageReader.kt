package kondol.mnist

import org.slf4j.LoggerFactory
import java.io.Closeable
import java.nio.file.Path
import java.util.*

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
    
    fun random(n: Int): List<Image> {
        if (n < 0) {
            throw IllegalArgumentException("n must be more than 0. but n = $n.")
        }
        val startIndex = Random(Date().time).nextInt(this.imageFileReader.numberOfImages - n)
        val imageList = mutableListOf<Image>()
        
        for (i in startIndex until (startIndex + n)) {
            imageList += this.readImage(i)
        }
        
        return imageList
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