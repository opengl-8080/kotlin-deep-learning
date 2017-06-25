package kondol.function

import kondol.matrix.Matrix

interface MatrixFunction {
    
    operator fun invoke(matrix: Matrix): Matrix
    
    fun numericalDifferential(x: Matrix): Matrix {
        val h = 1e-4
        return (this(x+h) - this(x-h)) / (2.0*h)
    }
}