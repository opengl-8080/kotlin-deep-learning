package kondol.function

import kondol.matrix.Matrix

class StepFunction: ActivationFunction {
    
    override fun invoke(matrix: Matrix) = matrix.map { if (it <= 0.0) 0.0 else 1.0 }
}
