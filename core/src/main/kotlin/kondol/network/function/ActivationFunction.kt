package kondol.network.function

import kondol.matrix.Matrix

interface ActivationFunction {
    
    operator fun invoke(matrix: Matrix): Matrix
}
