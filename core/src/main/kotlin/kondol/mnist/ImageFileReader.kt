package kondol.mnist

import java.io.Closeable
import java.io.RandomAccessFile
import java.nio.file.Path

internal class ImageFileReader (private val file: Path): Closeable {
    companion object {
        internal val NUMBER_OF_ROW = 28
        internal val NUMBER_OF_COL = 28
        private val PIXEL_NUMBER_OF_ONE_IMAGE = NUMBER_OF_ROW * NUMBER_OF_COL
        private val OFFSET_OF_FIRST_IMAGE = 16L
    }
    
    private val reader = RandomAccessFile(this.file.toFile(), "r")
    
    fun readPixels(imageIndex: Int): IntArray {
        if (imageIndex < 0) {
            throw IllegalArgumentException("imageIndex must be 0 or more. (but imageIndex=$imageIndex)")
        }
        
        val offset = OFFSET_OF_FIRST_IMAGE + (PIXEL_NUMBER_OF_ONE_IMAGE * imageIndex)
        this.reader.seek(offset)
        
        val pixels = IntArray(PIXEL_NUMBER_OF_ONE_IMAGE)
        
        for (i in 0 until PIXEL_NUMBER_OF_ONE_IMAGE) {
            pixels[i] = this.reader.read()
        }
        
        return pixels
    }
    
    override fun close() {
        this.reader.close()
    }
}
