package kondol.function

import kondol.matrix.Matrix

interface ActivationFunction {
    
    operator fun invoke(matrix: Matrix): Matrix
    
    companion object {
        val SIGMOID = SigmoidFunction()
    }
}
