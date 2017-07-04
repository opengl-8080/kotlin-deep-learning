package kondol.mnist

import kondol.matrix.Matrix

class Images (
    private val list: List<Image>
) {
    val imageMatrix: Matrix = this.toMatrix(Image::pixelData)
    val teacherMatrix: Matrix = this.toMatrix(Image::teacherData)
    
    private fun toMatrix(mapper: (Image) -> DoubleArray): Matrix {
        val rows = mutableListOf<DoubleArray>()

        this.list.forEach { image ->
            rows += mapper(image)
        }
        
        return Matrix(*rows.toTypedArray())
    }
}