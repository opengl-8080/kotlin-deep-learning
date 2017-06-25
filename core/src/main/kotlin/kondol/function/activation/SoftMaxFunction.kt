package kondol.function.activation

import kondol.matrix.Matrix

class SoftMaxFunction : ActivationFunction {
    
    override fun invoke(matrix: Matrix): Matrix {
        val max = matrix.max()
        val tmp = matrix - max
        val exp = tmp.exp()
        return exp / exp.sum()
    }
}
