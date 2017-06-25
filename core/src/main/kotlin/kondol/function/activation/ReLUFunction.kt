package kondol.function.activation

import kondol.matrix.Matrix

class ReLUFunction: ActivationFunction {
    
    override fun invoke(matrix: Matrix) = matrix.map { if (it < 0.0) 0.0 else it }
}
