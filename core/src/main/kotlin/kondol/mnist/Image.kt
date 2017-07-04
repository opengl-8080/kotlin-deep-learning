package kondol.mnist

import org.slf4j.LoggerFactory
import java.awt.Color
import java.awt.image.BufferedImage
import java.nio.file.Path
import java.util.*
import javax.imageio.ImageIO


class Image internal constructor (
    val label: Int,
    private val pixels: IntArray
) {
    private val logger = LoggerFactory.getLogger(Image::class.java)
    private val numberOfRow = ImageFileReader.NUMBER_OF_ROW
    private val numberOfCol = ImageFileReader.NUMBER_OF_COL
    
    val pixelData: DoubleArray = this.pixels.map(Int::toDouble).toDoubleArray()
    val teacherData: DoubleArray = label.let { 
        val data = DoubleArray(10)
        data[label] = 1.0
        data
    }
    
    fun export(out: Path) {
        val bufferedImage = BufferedImage(this.numberOfRow, this.numberOfCol, BufferedImage.TYPE_BYTE_GRAY)
        val graphics = bufferedImage.createGraphics()

        var i = 0
        for (row in 0 until this.numberOfRow) {
            for (col in 0 until this.numberOfCol) {
                val grayScale = this.pixels[i]
                graphics.color = Color(grayScale, grayScale, grayScale)
                graphics.drawRect(col, row, 1, 1)
                i++
            }
        }
        
        logger.info("export image to '$out'.")
        ImageIO.write(bufferedImage, "jpeg", out.toFile())
    }

    override fun toString() = "Image(label=$label, pixels=${Arrays.toString(pixels)})"
}
