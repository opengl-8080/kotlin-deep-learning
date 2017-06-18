package kondol.function

import kondol.matrix.Matrix

class SoftMaxFunction : ActivationFunction {
    
    override fun invoke(matrix: Matrix): Matrix {
        val exp = matrix.exp()
        return exp / exp.sum()
    }
}
