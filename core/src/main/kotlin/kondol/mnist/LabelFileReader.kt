package kondol.mnist

import java.io.Closeable
import java.io.RandomAccessFile
import java.nio.file.Path


internal class LabelFileReader (private val file: Path): Closeable {

    private val OFFSET_OF_FIRST_IMAGE = 8L
    private val reader = RandomAccessFile(this.file.toFile(), "r")

    fun readLabel(imageIndex: Int): Int {
        if (imageIndex < 0) {
            throw IllegalArgumentException("imageIndex must be 0 or more. (but imageIndex=$imageIndex)")
        }

        val position = OFFSET_OF_FIRST_IMAGE + imageIndex
        this.reader.seek(position)
        
        return this.reader.read()
    }

    override fun close() {
        this.reader.close()
    }
}