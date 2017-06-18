package kondol.function

import kondol.matrix.Matrix

class IdentityFunction: ActivationFunction {
    
    override fun invoke(matrix: Matrix) = matrix
}